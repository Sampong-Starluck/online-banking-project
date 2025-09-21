package org.sampong.onlinebanking.account.controller;

import lombok.RequiredArgsConstructor;
import org.sampong.onlinebanking._common.base.res.BaseResponse;
import org.sampong.onlinebanking._common.base.res.MessageResponse;
import org.sampong.onlinebanking._common.base.res.ObjectResponse;
import org.sampong.onlinebanking.account.controller.dto.request.AccountRequest;
import org.sampong.onlinebanking.account.controller.dto.res.AccountResponse;
import org.sampong.onlinebanking.account.controller.mapper.AccountRestMapper;
import org.sampong.onlinebanking.account.controller.rest.AccountRest;
import org.sampong.onlinebanking.account.service.AccountService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
public class AccountController implements AccountRest {

    private final AccountService service;
    private final AccountRestMapper mapper;

    @Override
    public ObjectResponse<AccountResponse> get(Long id) {
        return service.findById(id).map(mapper::toResponse)
                .map(BaseResponse::success)
                .orElseGet(BaseResponse::error);
    }

    @Override
    public ObjectResponse<AccountResponse> save(AccountRequest accountRequest) {
        return Optional.of(service.addNew(mapper.fromRequest(accountRequest)))
                .map(mapper::toResponse)
                .map(BaseResponse::success)
                .orElseGet(BaseResponse::error);
    }

    @Override
    public ObjectResponse<AccountResponse> update(AccountRequest accountRequest) {
        return Optional.of(service.updateObj(mapper.fromRequest(accountRequest)))
                .map(mapper::toResponse)
                .map(BaseResponse::success)
                .orElseGet(BaseResponse::error);
    }

    @Override
    public MessageResponse delete(Long id) {
        return null;
    }

    @Override
    public ObjectResponse<List<AccountResponse>> findAll() {
        return service.findAllList()
                .stream().map(i -> i.stream().map(mapper::toResponse).toList())
                .map(BaseResponse::success).findAny()
                .orElseGet(BaseResponse::error);
    }

    @Override
    public ObjectResponse<List<AccountResponse>> getAllAccountByCustomerId(Long id) {
        return Optional.of(service.getAccountsByCustomerId(id))
                .stream().map(i -> i.stream().map(mapper::toResponse).toList())
                .map(BaseResponse::success).findAny()
                .orElseGet(BaseResponse::error);
    }
}
