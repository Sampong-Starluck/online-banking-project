package org.sampong.onlinebanking.transfer.controller.dto.request;

import lombok.Getter;
import org.sampong.onlinebanking._common.base.request.PageRequest;

@Getter
public class TransactionPageRequest extends PageRequest {
    String sourceAccount = null;
    String destinationAccount = null;
}
