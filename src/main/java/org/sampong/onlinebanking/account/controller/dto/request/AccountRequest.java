package org.sampong.onlinebanking.account.controller.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.sampong.onlinebanking._common.base.request.ObjectIdRequest;
import org.sampong.onlinebanking._common.enumerate.AccountType;
import org.sampong.onlinebanking._common.enumerate.Currency;
import org.sampong.onlinebanking.account.model.Account;

import java.time.LocalDateTime;

/**
 * DTO for {@link Account}
 */
public record AccountRequest(
        @NotNull
        @Min(0)
        Long id,
        LocalDateTime issueDate,
        LocalDateTime expireDate,
        Double balance,
        Currency currency,
        AccountType accountType,
        ObjectIdRequest customer
) {
}