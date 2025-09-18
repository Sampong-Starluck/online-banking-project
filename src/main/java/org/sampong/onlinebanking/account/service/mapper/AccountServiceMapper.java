package org.sampong.onlinebanking.account.service.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.sampong.onlinebanking._common.configuration.MapperConfiguration;
import org.sampong.onlinebanking.account.model.Account;

@Mapper(config = MapperConfiguration.class)
public interface AccountServiceMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Account from(Account t, @MappingTarget Account entity);
}
