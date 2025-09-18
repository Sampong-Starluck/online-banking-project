package org.sampong.onlinebanking.account.service.implement;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.sampong.onlinebanking._common.annotation.Logger;
import org.sampong.onlinebanking._common.exception.CustomException;
import org.sampong.onlinebanking.account.model.Account;
import org.sampong.onlinebanking.account.repository.AccountRepository;
import org.sampong.onlinebanking.account.service.AccountService;
import org.sampong.onlinebanking.account.service.mapper.AccountServiceMapper;
import org.sampong.onlinebanking.customer.model.Customer;
import org.sampong.onlinebanking.customer.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Logger("Generated account number")
    private String generateAccountNumber(Customer cus) {
        var customerId = cus.getId();
        var dateChar = Long.toString(System.currentTimeMillis() * 365L).toCharArray();
        var date = String.valueOf(dateChar, 6, 6);
        var counter = ThreadLocalRandom.current().nextInt(1, 10);
        return "00" + date + customerId + counter;
    }
}
