package org.sampong.onlinebanking.customer.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.sampong.onlinebanking._common.configuration.MapperConfiguration;
import org.sampong.onlinebanking.customer.controller.dto.req.CustomerRequest;
import org.sampong.onlinebanking.customer.controller.dto.res.CustomerResponse;
import org.sampong.onlinebanking.customer.model.Customer;

@Mapper(config = MapperConfiguration.class)
public interface CustomerRestMapper {
    CustomerRestMapper INSTANCE = Mappers.getMapper(CustomerRestMapper.class);

    Customer fromRequest(CustomerRequest customerRequest);
    @Mapping(target = "customerFullName", expression = "java(customer.getLastName() + \" \" + customer.getFirstName())")
    CustomerResponse fromResponse(Customer customer);
}
