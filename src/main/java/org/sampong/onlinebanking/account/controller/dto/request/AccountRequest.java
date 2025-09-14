package org.sampong.onlinebanking.account.controller.dto.request;

import org.sampong.onlinebanking._common.base.request.ObjectIdRequest;
import org.sampong.onlinebanking._common.enumerate.Currency;
import org.sampong.onlinebanking.account.model.Account;

import java.time.LocalDateTime;

/**
 * DTO for {@link Account}
 */
public record AccountRequest(Long id, String accountNumber, LocalDateTime issueDate, LocalDateTime expireDate,
                             Double balance, Currency currency, ObjectIdRequest customer) {
}