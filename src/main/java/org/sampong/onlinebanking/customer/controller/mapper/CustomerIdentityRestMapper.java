package org.sampong.onlinebanking.customer.controller.mapper;

import jakarta.persistence.Id;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.sampong.onlinebanking._common.configuration.MapperConfiguration;
import org.sampong.onlinebanking.customer.controller.dto.req.CustomerIdentificationRequest;
import org.sampong.onlinebanking.customer.controller.dto.res.CustomerIdentificationResponse;
import org.sampong.onlinebanking.customer.model.CustomerIdentification;

@Mapper(config = MapperConfiguration.class)
public interface CustomerIdentityRestMapper {
    CustomerIdentityRestMapper INSTANCE = Mappers.getMapper(CustomerIdentityRestMapper.class);
    @Mappings({
            @Mapping(target = "customer.id", source = "customer.id")
    })
    CustomerIdentification fromRequest(CustomerIdentificationRequest customerIdentification);
    CustomerIdentificationResponse toResponse(CustomerIdentification customerIdentification);
}
