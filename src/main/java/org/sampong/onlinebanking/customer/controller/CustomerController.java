package org.sampong.onlinebanking.customer.controller;

import lombok.RequiredArgsConstructor;
import org.sampong.onlinebanking._common.base.res.BaseResponse;
import org.sampong.onlinebanking._common.base.res.MessageResponse;
import org.sampong.onlinebanking._common.base.res.ObjectResponse;
import org.sampong.onlinebanking._common.base.res.PageResponse;
import org.sampong.onlinebanking.customer.controller.dto.req.CustomerPageRequest;
import org.sampong.onlinebanking.customer.controller.dto.req.CustomerRequest;
import org.sampong.onlinebanking.customer.controller.dto.res.CustomerResponse;
import org.sampong.onlinebanking.customer.controller.mapper.CustomerRestMapper;
import org.sampong.onlinebanking.customer.controller.rest.CustomerRest;
import org.sampong.onlinebanking.customer.service.CustomerService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
public class CustomerController implements CustomerRest {
    private final CustomerService service;
    private final CustomerRestMapper mapper;

    @Override
    public ObjectResponse<CustomerResponse> get(Long id) {
        return service.findById(id)
                .map(mapper::fromResponse)           // Map Customer → CustomerResponse
                .map(BaseResponse::success)          // Wrap in BaseResponse.success
                .orElseGet(() -> BaseResponse.error("Customer not found"));
    }

    @Override
    public ObjectResponse<CustomerResponse> save(CustomerRequest customerRequest) {
        return Optional.of(service.addNew(mapper.fromRequest(customerRequest)))
                .map(mapper::fromResponse).map(BaseResponse::success)
                .orElseGet(() -> BaseResponse.error("Customer cannot be added"));
    }

    @Override
    public ObjectResponse<CustomerResponse> update(CustomerRequest customerRequest) {
        return Optional.of(service.updateObj(mapper.fromRequest(customerRequest)))
                .map(mapper::fromResponse).map(BaseResponse::success)
                .orElseGet(() -> BaseResponse.error("Customer cannot be added"));
    }

    @Override
    public MessageResponse delete(Long id) {
        return null;
    }

    @Override
    public ObjectResponse<List<CustomerResponse>> findAll() {
        return service.findAllList()
                .map(list -> list.stream()
                        .map(mapper::fromResponse)
                        .toList())
                .map(BaseResponse::success)
                .orElseGet(() -> BaseResponse.error("No customers found"));
    }

    @Override
    public PageResponse<CustomerResponse> getPageList(CustomerPageRequest customerPageRequest) {
        var customerPage = service.findAllPage(customerPageRequest).map(mapper::fromResponse);
        return BaseResponse.paginated(customerPage);
    }
}
