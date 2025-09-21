package org.sampong.onlinebanking.account.repository;

import org.sampong.onlinebanking._common.base.BaseRepository;
import org.sampong.onlinebanking.account.model.Account;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends BaseRepository<Account> {
    Optional<List<Account>> findAllByCustomerIdAndStatusTrue(Long customerId);
    Optional<Account> findByAccountNumberAndStatusTrue(String accNumber);
}
