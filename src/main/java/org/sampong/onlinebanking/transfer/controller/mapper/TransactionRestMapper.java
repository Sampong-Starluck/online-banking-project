package org.sampong.onlinebanking.transfer.controller.mapper;

import jakarta.transaction.Transactional;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.sampong.onlinebanking._common.configuration.MapperConfiguration;
import org.sampong.onlinebanking.transfer.controller.dto.request.DepositRequest;
import org.sampong.onlinebanking.transfer.controller.dto.request.TransactionRequest;
import org.sampong.onlinebanking.transfer.controller.dto.request.TransferRequest;
import org.sampong.onlinebanking.transfer.controller.dto.request.WithdrawRequest;
import org.sampong.onlinebanking.transfer.controller.dto.res.TransactionResponse;
import org.sampong.onlinebanking.transfer.model.Transaction;

@Mapper(config = MapperConfiguration.class)
public interface TransactionRestMapper {

    TransactionRestMapper INSTANCE = Mappers.getMapper(TransactionRestMapper.class);

    TransactionResponse to(Transaction transaction);
    @Mapping(target = "srcAccountId", source = "accountId")
    @Mapping(target = "type", expression = "java(org.sampong.onlinebanking._common.enumerate.TranceType.WITHDRAW)")
    TransferRequest from(WithdrawRequest withdrawRequest);
    @Mapping(target = "type", expression = "java(org.sampong.onlinebanking._common.enumerate.TranceType.DEPOSIT)")
    @Mapping(target = "targetAccountId", source = "accountId")
    TransferRequest from(DepositRequest depositRequest);
}
