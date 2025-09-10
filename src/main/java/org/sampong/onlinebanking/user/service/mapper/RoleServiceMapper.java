package org.sampong.onlinebanking.user.service.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.sampong.onlinebanking._common.configuration.MapperConfiguration;
import org.sampong.onlinebanking.user.model.Role;

@Mapper(config = MapperConfiguration.class)
public interface RoleServiceMapper {

    RoleServiceMapper INSTANCE = Mappers.getMapper(RoleServiceMapper.class);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Role from(Role t, @MappingTarget Role entity);
}
