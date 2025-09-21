package org.sampong.onlinebanking.transfer.controller.dto.request;

import org.sampong.onlinebanking._common.base.request.ObjectIdRequest;

import java.io.Serializable;

/**
 * DTO for {@link org.sampong.onlinebanking.transfer.model.Transaction}
 */
public record TransactionRequest(Long id, String sourceAccountNumber, ObjectIdRequest sourceAccount,
                                 String destinationAccountNumber, ObjectIdRequest destinationAccount, Double amount,
                                 String description, String tranceNumber) implements Serializable {
}