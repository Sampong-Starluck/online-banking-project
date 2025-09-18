package org.sampong.onlinebanking.transfer.controller;

import org.sampong.onlinebanking._common.base.res.ObjectResponse;
import org.sampong.onlinebanking._common.base.res.PageResponse;
import org.sampong.onlinebanking.transfer.controller.dto.request.TransactionPageRequest;
import org.sampong.onlinebanking.transfer.controller.dto.request.TransactionRequest;
import org.sampong.onlinebanking.transfer.controller.dto.res.TransactionResponse;
import org.sampong.onlinebanking.transfer.controller.rest.TransactionRest;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController implements TransactionRest {
    @Override
    public ObjectResponse<TransactionPageRequest> transfer(TransactionRequest transactionRequest) {
        return null;
    }

    @Override
    public PageResponse<TransactionPageRequest> getPageList(TransactionResponse transactionResponse) {
        return null;
    }

    @Override
    public ObjectResponse<TransactionPageRequest> get(Long id) {
        return null;
    }
}
