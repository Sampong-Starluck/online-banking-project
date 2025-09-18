package org.sampong.onlinebanking.user.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.sampong.onlinebanking._common.configuration.MapperConfiguration;
import org.sampong.onlinebanking.user.controller.dto.response.UserResponse;
import org.sampong.onlinebanking.user.model.Users;

@Mapper(config = MapperConfiguration.class)
public interface UserRestMapper {

    UserRestMapper INSTANCE = Mappers.getMapper(UserRestMapper.class);

    UserResponse toResponse(Users user);
}
