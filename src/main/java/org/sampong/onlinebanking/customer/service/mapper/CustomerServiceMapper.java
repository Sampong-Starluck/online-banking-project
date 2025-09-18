package org.sampong.onlinebanking.customer.service.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.sampong.onlinebanking._common.configuration.MapperConfiguration;
import org.sampong.onlinebanking.customer.model.Customer;

@Mapper(config = MapperConfiguration.class)
public interface CustomerServiceMapper {
    CustomerServiceMapper INSTANCE = Mappers.getMapper(CustomerServiceMapper.class);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Customer from(Customer t, @MappingTarget Customer customer);
}
