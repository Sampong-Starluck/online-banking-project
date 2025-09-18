package org.sampong.onlinebanking.transfer.controller.dto.request;

import org.sampong.onlinebanking._common.base.request.PageRequest;

public class TransactionPageRequest extends PageRequest {
    String sourceAccount = null;
    String destinationAccount = null;
}
