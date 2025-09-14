package org.sampong.onlinebanking.customer.controller;

import lombok.RequiredArgsConstructor;
import org.sampong.onlinebanking._common.base.res.BaseResponse;
import org.sampong.onlinebanking.customer.controller.dto.req.CustomerIdentificationRequest;
import org.sampong.onlinebanking.customer.controller.dto.res.CustomerIdentificationResponse;
import org.sampong.onlinebanking.customer.controller.mapper.CustomerIdentityRestMapper;
import org.sampong.onlinebanking.customer.controller.rest.CustomerIdentityRest;
import org.sampong.onlinebanking.customer.service.CustomerIdentityService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CustomerIdentityController implements CustomerIdentityRest {

    private final CustomerIdentityService service;
    private final CustomerIdentityRestMapper mapper;

    @Override
    public BaseResponse<CustomerIdentificationResponse> get(Long id) {
        return service.findById(id).map(mapper::toResponse).map(BaseResponse::success)
                .orElse(BaseResponse.error(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public BaseResponse<CustomerIdentificationResponse> save(CustomerIdentificationRequest customerIdentificationRequest) {
        return Optional.ofNullable(service.addNew(mapper.fromRequest(customerIdentificationRequest)))
                .map(mapper::toResponse).map(BaseResponse::success)
                .orElse(BaseResponse.error(HttpStatus.BAD_REQUEST.getReasonPhrase()));
    }

    @Override
    public BaseResponse<CustomerIdentificationResponse> update(CustomerIdentificationRequest customerIdentificationRequest) {
        return Optional.ofNullable(service.updateObj(mapper.fromRequest(customerIdentificationRequest)))
                .map(mapper::toResponse).map(BaseResponse::success)
                .orElse(BaseResponse.error(HttpStatus.BAD_REQUEST.getReasonPhrase()));
    }

    @Override
    public BaseResponse<Void> delete(Long id) {
        service.delete(id);
        return BaseResponse.withCode(200, "Delete identification successfully");
    }

    @Override
    public BaseResponse<List<CustomerIdentificationResponse>> findAll() {
        return service.findAllList().stream()
                .map(t -> t.stream().map(mapper::toResponse).toList())
                .map(BaseResponse::success).findAny()
                .orElse(BaseResponse.error(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }
}
