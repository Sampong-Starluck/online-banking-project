package org.sampong.onlinebanking.account.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.sampong.onlinebanking._common.configuration.MapperConfiguration;
import org.sampong.onlinebanking.account.controller.dto.request.AccountRequest;
import org.sampong.onlinebanking.account.controller.dto.res.AccountResponse;
import org.sampong.onlinebanking.account.model.Account;

@Mapper(config = MapperConfiguration.class)
public interface AccountRestMapper {
    AccountRestMapper INSTANCE = Mappers.getMapper(AccountRestMapper.class);

    @Mappings({
            @Mapping(target = "customer.id", source = "customer.id")
    })
    Account fromRequest(AccountRequest accountRequest);
    AccountResponse toResponse(Account account);
}
