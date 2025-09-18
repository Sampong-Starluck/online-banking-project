package org.sampong.onlinebanking.transfer.controller.rest;

import org.sampong.onlinebanking._common.base.res.ObjectResponse;
import org.sampong.onlinebanking._common.base.res.PageResponse;
import org.sampong.onlinebanking._common.base.rest.BasePageRest;
import org.sampong.onlinebanking._common.constant.AppConstants;
import org.sampong.onlinebanking.transfer.controller.dto.request.TransactionPageRequest;
import org.sampong.onlinebanking.transfer.controller.dto.request.TransactionRequest;
import org.sampong.onlinebanking.transfer.controller.dto.res.TransactionResponse;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(AppConstants.WEB_PATH+"/transaction")
public interface TransactionRest {
    ObjectResponse<TransactionPageRequest> transfer(TransactionRequest transactionRequest);
    PageResponse<TransactionPageRequest> getPageList(TransactionResponse transactionResponse);
    ObjectResponse<TransactionPageRequest> get(Long id);

}
