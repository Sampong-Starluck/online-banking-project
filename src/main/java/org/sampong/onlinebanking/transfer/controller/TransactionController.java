package org.sampong.onlinebanking.transfer.controller;

import lombok.RequiredArgsConstructor;
import org.sampong.onlinebanking._common.base.res.BaseResponse;
import org.sampong.onlinebanking._common.base.res.ObjectResponse;
import org.sampong.onlinebanking._common.base.res.PageResponse;
import org.sampong.onlinebanking.transfer.controller.dto.request.*;
import org.sampong.onlinebanking.transfer.controller.dto.res.TransactionResponse;
import org.sampong.onlinebanking.transfer.controller.mapper.TransactionRestMapper;
import org.sampong.onlinebanking.transfer.controller.rest.TransactionRest;
import org.sampong.onlinebanking.transfer.service.TranceService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
public class TransactionController implements TransactionRest {

    private final TranceService service;
    private final TransactionRestMapper mapper;

    @Override
    public ObjectResponse<TransactionResponse> transfer(TransferRequest transferRequest) {
        return Optional.ofNullable(service.transferBalance(transferRequest))
                .map(mapper::to).map(BaseResponse::success)
                .orElseGet(BaseResponse::error);
    }

    @Override
    public PageResponse<TransactionResponse> getPageList(TransactionPageRequest transactionPageRequest) {
        var tranceList = service.findAllPage(transactionPageRequest).map(mapper::to);
        return BaseResponse.paginated(tranceList);
    }

    @Override
    public ObjectResponse<TransactionResponse> getById(Long id) {
        return Optional.ofNullable(service.findById(id))
                .flatMap(byId -> byId.map(mapper::to))
                .map(BaseResponse::success)
                .orElseGet(BaseResponse::error);
    }

    @Override
    public ObjectResponse<TransactionResponse> deposit(DepositRequest depositRequest) {
        return Optional.ofNullable(service.depositBalance(mapper.from(depositRequest)))
                .map(mapper::to).map(BaseResponse::success)
                .orElseGet(BaseResponse::error);
    }

    @Override
    public ObjectResponse<TransactionResponse> withdraw(WithdrawRequest withdrawRequest) {
        return Optional.ofNullable(service.withdrawBalance(mapper.from(withdrawRequest)))
                .map(mapper::to).map(BaseResponse::success)
                .orElseGet(BaseResponse::error);
    }
}
