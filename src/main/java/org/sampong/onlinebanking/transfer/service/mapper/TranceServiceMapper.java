package org.sampong.onlinebanking.transfer.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.sampong.onlinebanking._common.configuration.MapperConfiguration;
import org.sampong.onlinebanking.transfer.model.Transaction;

@Mapper(config= MapperConfiguration.class)
public interface TranceServiceMapper {

    TranceServiceMapper INSTANCE = Mappers.getMapper(TranceServiceMapper.class);
    Transaction from(Transaction t, @MappingTarget Transaction entity);
}
