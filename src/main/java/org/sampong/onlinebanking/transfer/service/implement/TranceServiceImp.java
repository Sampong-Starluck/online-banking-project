package org.sampong.onlinebanking.transfer.service.implement;

import jakarta.persistence.OptimisticLockException;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sampong.onlinebanking._common.annotation.AccountLock;
import org.sampong.onlinebanking._common.enumerate.TranceStatus;
import org.sampong.onlinebanking._common.enumerate.TranceType;
import org.sampong.onlinebanking._common.exception.CustomException;
import org.sampong.onlinebanking.account.model.Account;
import org.sampong.onlinebanking.account.service.AccountService;
import org.sampong.onlinebanking.transfer.controller.dto.request.TransactionPageRequest;
import org.sampong.onlinebanking.transfer.controller.dto.request.TransferRequest;
import org.sampong.onlinebanking.transfer.model.DeadLetterTransaction;
import org.sampong.onlinebanking.transfer.model.Transaction;
import org.sampong.onlinebanking.transfer.repository.DeadLetterTranceRepository;
import org.sampong.onlinebanking.transfer.repository.TranceRepository;
import org.sampong.onlinebanking.transfer.service.TranceService;
import org.sampong.onlinebanking.transfer.service.mapper.TranceServiceMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.retry.annotation.Recover;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class TranceServiceImp implements TranceService {

    private final TranceRepository tranceRepository;
    private final TranceServiceMapper mapper;
    private final AccountService accountService;
    private final DeadLetterTranceRepository dlqRepository;

    @Override
    public Page<Transaction> findAllPage(TransactionPageRequest transactionPageRequest) {
        var page = transactionPageRequest.getPage();
        var size = transactionPageRequest.getSize();
        return tranceRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (transactionPageRequest.getSourceAccount() != null) {
                predicates.add(cb.equal(root.get("sourceAccount"), transactionPageRequest.getSourceAccount()));
            }
            if (transactionPageRequest.getDestinationAccount() != null) {
                predicates.add(cb.equal(root.get("destinationAccount"), transactionPageRequest.getDestinationAccount()));
            }

            if (transactionPageRequest.getQuery() != null) {
                predicates.add(cb.equal(root.get("tranceNumber"), transactionPageRequest.getQuery()));
            }

            predicates.add(cb.isTrue(root.get("status")));
            return cb.and(predicates.toArray(new Predicate[0]));
        }, pagination(page, size, "id", Sort.Direction.DESC));
    }

    @Override
    public Optional<Transaction> findById(Long id) {
        return Optional.ofNullable(tranceRepository.findByIdAndStatusTrue(id).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Transaction not found")));
    }

    @Override
    public Optional<List<Transaction>> findAllList() {
        return Optional.empty();
    }

    @Override
    public Transaction addNew(Transaction transaction) {
        return tranceRepository.save(transaction);
    }

    @Override
    public Transaction updateObj(Transaction transaction) {
        var old = findById(transaction.getId());
        if (old.isEmpty()) {
            throw new CustomException(HttpStatus.NOT_FOUND, "Transaction not found");
        }
        var update = mapper.from(transaction,  old.get());
        return tranceRepository.save(update);
    }

    @Override
    public void delete(Long id) {
        tranceRepository.delete(id);
    }

    @Transactional
    @Override
    public Transaction transferBalance(TransferRequest request) {
        var result = processTransaction(request);

        if (!"SUCCESS".equals(result)) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "Transfer failed");
        } else {
            Account src = accountService.findById(request.srcAccountId()).orElseThrow();
            Account dest = accountService.findById(request.targetAccountId()).orElseThrow();
            var trance = new Transaction();
            trance.setAmount(request.balance());
            trance.setTranceNumber(generateTranceNumber());
            trance.setDescription("Account id: " + request.srcAccountId() + " transfer: " + request.balance() + request.currency().name() + " to account: " + request.targetAccountId() );
            trance.setDestinatioAccount(dest);
            trance.setSourceAccount(src);
            trance.setSourceAccountNumber(src.getAccountNumber());
            trance.setDestinationAccountNumber(dest.getAccountNumber());
            trance.setTranceType(TranceType.TRANSFER);
            trance.setCurrency(src.getCurrency());
            return addNew(trance);
        }
    }

    @Transactional
    @Override
    public Transaction withdrawBalance(TransferRequest request) {
        var result = processTransaction(request);

        if (!"SUCCESS".equals(result)) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "Transfer failed");
        } else {

            Account src = accountService.findById(request.srcAccountId()).orElseThrow();
            var trance = new Transaction();
            trance.setAmount(request.balance());
            trance.setTranceNumber(generateTranceNumber());
            trance.setDescription(request.balance() + request.currency().name() + " from account: " + request.srcAccountId() );
            trance.setSourceAccount(src);
            trance.setTranceType(TranceType.WITHDRAW);
            trance.setSourceAccountNumber(src.getAccountNumber());
            trance.setCurrency(request.currency());
            return addNew(trance);
        }
    }

    @Transactional
    @Override
    public Transaction depositBalance(TransferRequest request) {
        var result = processTransaction(request);

        if (!"SUCCESS".equals(result)) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "Transfer failed");
        } else {
            Account dest = accountService.findById(request.targetAccountId()).orElseThrow();
            var trance = new Transaction();
            trance.setAmount(request.balance());
            trance.setTranceNumber(generateTranceNumber());
            trance.setDescription("Account: "+request.targetAccountId()+" received " + request.balance()+ " " + request.currency().name());
            trance.setDestinatioAccount(dest);
            trance.setDestinationAccountNumber(dest.getAccountNumber());
            trance.setTranceType(TranceType.DEPOSIT);
            trance.setCurrency(request.currency());
            return addNew(trance);
        }
    }

    private String generateTranceNumber() {
        var id = UUID.randomUUID().toString().replace("-", "");
        return "trxn-" + id + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date().getTime());
    }

    @Transactional
    @AccountLock(values = { "#request.accountId", "#request.desAccountId" })
    @Retryable(
            value = OptimisticLockException.class,
            maxAttempts = 3,
            delay = 200
    )
    public String processTransaction(TransferRequest request) {
        return switch (request.type()) {
            case TRANSFER -> accountService.transfer(request);
            case WITHDRAW -> accountService.withdraw(request);
            case DEPOSIT -> accountService.deposit(request);
        };
    }


    // Retry exhausted → record DLQ
    @Recover
    public String recover(OptimisticLockException ex, TransferRequest request) {
        log.error("❌ Transaction permanently failed, moved to DLQ: {}", request);

        DeadLetterTransaction dlq = new DeadLetterTransaction();
        dlq.setFromAccountId(request.srcAccountId());
        dlq.setToAccountId(request.targetAccountId());
        dlq.setAmount(request.balance());
        dlq.setType(request.type().name());
        dlq.setCurrency(request.currency());
        dlq.setReason("Failed after 3 retries: " + ex.getMessage());
        dlq.setTrxnStatus(TranceStatus.PENDING);

        dlqRepository.save(dlq);
        return "FAILED: MOVED_TO_DLQ";
    }
}
