package org.sampong.onlinebanking.transfer.service;

import org.sampong.onlinebanking._common.base.service.BasePageService;
import org.sampong.onlinebanking.transfer.controller.dto.request.TransactionPageRequest;
import org.sampong.onlinebanking.transfer.controller.dto.request.TransferRequest;
import org.sampong.onlinebanking.transfer.model.Transaction;

public interface TranceService extends BasePageService<Transaction, TransactionPageRequest> {

    Transaction transferBalance(TransferRequest request);
    Transaction withdrawBalance(TransferRequest request);
    Transaction depositBalance(TransferRequest request);
    String processTransaction(TransferRequest request);
}
