package org.sampong.onlinebanking.account.controller.dto.res;

import org.sampong.onlinebanking._common.base.request.ObjectIdRequest;
import org.sampong.onlinebanking._common.enumerate.AccountType;
import org.sampong.onlinebanking._common.enumerate.Currency;
import org.sampong.onlinebanking.account.model.Account;
import org.sampong.onlinebanking.customer.controller.dto.res.CustomerResponse;

import java.time.LocalDateTime;

/**
 * DTO for {@link Account}
 */
public record AccountResponse(
        Long id,
        String accountNumber,
        LocalDateTime issueDate,
        LocalDateTime expireDate,
        Double balance,
        Currency currency,
        AccountType accountType,
        CustomerResponse customer
) {
}