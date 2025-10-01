package org.sampong.onlinebanking.transfer.service;

import org.sampong.onlinebanking._common.base.service.BasePageService;
import org.sampong.onlinebanking.transfer.controller.dto.request.TransactionPageRequest;
import org.sampong.onlinebanking.transfer.controller.dto.request.TransferRequest;
import org.sampong.onlinebanking.transfer.model.Transaction;

public interface TranceService extends BasePageService<Transaction, TransactionPageRequest> {

    Transaction transferBalance(TransferRequest request) throws InterruptedException;
    Transaction withdrawBalance(TransferRequest request) throws InterruptedException;
    Transaction depositBalance(TransferRequest request) throws InterruptedException;
    String processTransaction(TransferRequest request) throws InterruptedException;
}
