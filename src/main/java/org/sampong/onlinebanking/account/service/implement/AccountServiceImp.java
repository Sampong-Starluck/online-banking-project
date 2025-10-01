package org.sampong.onlinebanking.account.service.implement;

import lombok.RequiredArgsConstructor;
import org.sampong.onlinebanking._common.annotation.Logger;
import org.sampong.onlinebanking._common.enumerate.Currency;
import org.sampong.onlinebanking._common.exception.CustomException;
import org.sampong.onlinebanking.account.model.Account;
import org.sampong.onlinebanking.account.repository.AccountRepository;
import org.sampong.onlinebanking.account.service.AccountService;
import org.sampong.onlinebanking.account.service.mapper.AccountServiceMapper;
import org.sampong.onlinebanking.customer.model.Customer;
import org.sampong.onlinebanking.customer.service.CustomerService;
import org.sampong.onlinebanking.transfer.controller.dto.request.TransferRequest;
import org.springframework.http.HttpStatus;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class AccountServiceImp implements AccountService {

    private final AccountRepository repository;
    private final AccountServiceMapper mapper;
    private final CustomerService customerService;

    @Override
    @Retryable(
            value = ObjectOptimisticLockingFailureException.class,
            maxAttempts = 3,
            delay = 200
    )
    public Optional<Account> findById(Long id) {
        return Optional.ofNullable(repository.findByIdAndStatusTrue(id)).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Account not found"));
    }

    @Override
    public Optional<List<Account>> findAllList() {
        return Optional.of(repository.findAllByStatusTrue()).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Account not found"));
    }

    @Override
    public Account addNew(Account account) {
        if (account.getId() != 0L) {
            throw new CustomException(HttpStatus.CONFLICT, "Account id is already exists");
        }
        var customer = customerService.findById(account.getCustomer().getId());
        if (customer.isEmpty()) {
            throw new CustomException(HttpStatus.NOT_FOUND, "Customer not found");
        }
        var acc = generateAccountNumber(customer.get());
        account.setAccountNumber(acc);
        System.out.println("New account number: " + acc);
        return repository.save(account);
    }

    @Override
    public Account updateObj(Account account) {
        var old = findById(account.getId());
        if (old.isEmpty()) {
            throw new CustomException(HttpStatus.NOT_FOUND, "Account not found");
        }
        var update = mapper.from(account, old.get());
        return repository.save(update);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Account> getAccountsByCustomerId(Long customerId) {
        return Optional.of(repository.findAllByCustomerIdAndStatusTrue(customerId)).get().orElse(List.of());
    }

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        return Optional.ofNullable(repository.findByAccountNumberAndStatusTrue(accountNumber)).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Account not found"));
    }

    @Override
    @Transactional
    public String withdraw(TransferRequest r) {
        Account src = repository.findById(r.srcAccountId()).orElseThrow();

        double amount = convertIfNeeded(r.balance(), r.currency(), src.getCurrency());

        if (hasSufficientFunds(src, amount)) {
            return "FAILED: Insufficient funds";
        }

        updateBalance(src, -amount);
        repository.save(src);
        return "SUCCESS";
    }

    @Override
    @Transactional
    public String deposit(TransferRequest r) {
        Account dest = repository.findById(r.targetAccountId()).orElseThrow();

        double amount = convertIfNeeded(r.balance(), r.currency(), dest.getCurrency());

        updateBalance(dest, amount);
        repository.save(dest);
        return "SUCCESS";
    }

    @Override
    @Transactional
    public String transfer(TransferRequest r) {
        Account src = repository.findById(r.srcAccountId()).orElseThrow();
        Account dest = repository.findById(r.targetAccountId()).orElseThrow();

        double srcAmount  = convertIfNeeded(r.balance(), r.currency(), src.getCurrency());
        double destAmount = convertIfNeeded(r.balance(), r.currency(), dest.getCurrency());

        if (hasSufficientFunds(src, srcAmount)) {
            return "FAILED: Insufficient funds";
        }

        updateBalance(src, -srcAmount);
        updateBalance(dest, destAmount);

        repository.saveAll(List.of(src, dest));
        return "SUCCESS";
    }

    /* ------------------------
       🔽 Helper Methods
       ------------------------ */

    private double convertIfNeeded(double amount, Currency fromCurrency, Currency toCurrency) {
        if (fromCurrency == toCurrency) return amount;
        return convertMoney(amount, toCurrency);
    }

    private boolean hasSufficientFunds(Account acc, double required) {
        return !(acc.getBalance() >= required);
    }

    private void updateBalance(Account acc, double delta) {
        acc.setBalance(acc.getBalance() + delta);
    }

    private Double convertMoney(Double money, Currency targetCurrency) {
        return money / targetCurrency.getExchangeRate();
    }

    @Logger("Generated account number")
    private String generateAccountNumber(Customer cus) {
        var customerId = cus.getId();
        var dateChar = Long.toString(System.currentTimeMillis() * 365L).toCharArray();
        var date = String.valueOf(dateChar, 6, 6);
        var counter = ThreadLocalRandom.current().nextInt(1, 10);
        return "00" + date + customerId + counter;
    }
}
