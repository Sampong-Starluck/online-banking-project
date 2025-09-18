package org.sampong.onlinebanking.transfer.controller.mapper;

import jakarta.transaction.Transactional;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.sampong.onlinebanking._common.configuration.MapperConfiguration;
import org.sampong.onlinebanking.transfer.controller.dto.request.TransactionRequest;
import org.sampong.onlinebanking.transfer.controller.dto.res.TransactionResponse;
import org.sampong.onlinebanking.transfer.model.Transaction;

@Mapper(config = MapperConfiguration.class)
public interface TransactionRestMapper {

    TransactionRestMapper INSTANCE = Mappers.getMapper(TransactionRestMapper.class);

    Transaction from(TransactionRequest transactionRequest);
    TransactionResponse to(Transaction transaction);
}
