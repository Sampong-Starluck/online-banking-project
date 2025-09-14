package org.sampong.onlinebanking.account.service;

import org.sampong.onlinebanking._common.base.service.BaseService;
import org.sampong.onlinebanking.account.model.Account;

import java.util.List;

public interface AccountService extends BaseService<Account> {
    List<Account> getAccountsByCustomerId(Long customerId);
}
