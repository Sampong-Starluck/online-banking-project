package org.sampong.onlinebanking.account.service.implement;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.sampong.onlinebanking._common.exception.CustomException;
import org.sampong.onlinebanking.account.model.Account;
import org.sampong.onlinebanking.account.repository.AccountRepository;
import org.sampong.onlinebanking.account.service.AccountService;
import org.sampong.onlinebanking.account.service.mapper.AccountServiceMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImp implements AccountService {

    private final AccountRepository repository;
    private final AccountServiceMapper mapper;

    @Override
    public Optional<Account> findById(Long id) {
        return Optional.ofNullable(repository.findByIdAndStatusTrue(id)).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Account not found"));
    }

    @Override
    public Optional<List<Account>> findAllList() {
        return Optional.of(repository.findAllByStatusTrue()).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Account not found"));
    }

    @Override
    public Account addNew(Account account) {
        if (account.getId() != null) {
            throw new CustomException(HttpStatus.CONFLICT, "Account id is already exists");
        }
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
}
