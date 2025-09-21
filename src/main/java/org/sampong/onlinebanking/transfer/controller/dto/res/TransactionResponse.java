package org.sampong.onlinebanking.transfer.controller.dto.res;

import org.sampong.onlinebanking.account.controller.dto.res.AccountResponse;

import java.io.Serializable;

/**
 * DTO for {@link org.sampong.onlinebanking.transfer.model.Transaction}
 */
public record TransactionResponse(Long id, String sourceAccountNumber, AccountResponse sourceAccount,
                                  String destinationAccountNumber, AccountResponse destinatioAccount, Double amount,
                                  String description, String tranceNumber) implements Serializable {
}