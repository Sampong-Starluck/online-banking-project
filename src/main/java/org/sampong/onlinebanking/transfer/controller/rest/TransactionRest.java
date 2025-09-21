package org.sampong.onlinebanking.transfer.controller.rest;

import org.sampong.onlinebanking._common.base.res.ObjectResponse;
import org.sampong.onlinebanking._common.base.res.PageResponse;
import org.sampong.onlinebanking._common.base.rest.BasePageRest;
import org.sampong.onlinebanking._common.constant.AppConstants;
import org.sampong.onlinebanking.transfer.controller.dto.request.*;
import org.sampong.onlinebanking.transfer.controller.dto.res.TransactionResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(AppConstants.WEB_PATH+"/transaction")
public interface TransactionRest {
    @GetMapping("/list")
    PageResponse<TransactionResponse> getPageList(TransactionPageRequest transactionPageRequest);
    @GetMapping("/{id}")
    ObjectResponse<TransactionResponse> getById(Long id);
    @PostMapping("/transfer")
    ObjectResponse<TransactionResponse> transfer(TransferRequest transferRequest);
    @PostMapping("/deposit")
    ObjectResponse<TransactionResponse> deposit(DepositRequest depositRequest);
    @PostMapping("/withdraw")
    ObjectResponse<TransactionResponse> withdraw(WithdrawRequest withdrawRequest);
}
