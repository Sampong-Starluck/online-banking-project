package org.sampong.onlinebanking.account.service;

import org.sampong.onlinebanking._common.base.service.BaseService;
import org.sampong.onlinebanking.account.model.Account;
import org.sampong.onlinebanking.transfer.controller.dto.request.TransferRequest;

import java.util.List;
import java.util.Optional;

public interface AccountService extends BaseService<Account> {
    List<Account> getAccountsByCustomerId(Long customerId);
    Optional<Account> findByAccountNumber(String accountNumber);
    String deposit(TransferRequest r);
    String withdraw(TransferRequest r);
    String transfer(TransferRequest r);
}
