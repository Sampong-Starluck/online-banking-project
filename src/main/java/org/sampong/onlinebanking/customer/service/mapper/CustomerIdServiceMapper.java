package org.sampong.onlinebanking.customer.service.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.sampong.onlinebanking._common.configuration.MapperConfiguration;
import org.sampong.onlinebanking.customer.model.CustomerIdentification;

@Mapper(config = MapperConfiguration.class)
public interface CustomerIdServiceMapper {
    CustomerIdServiceMapper INSTANCE = Mappers.getMapper(CustomerIdServiceMapper.class);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CustomerIdentification from(CustomerIdentification t, @MappingTarget CustomerIdentification entity);
}
