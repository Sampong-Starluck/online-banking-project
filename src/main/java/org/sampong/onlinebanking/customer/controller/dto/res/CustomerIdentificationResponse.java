package org.sampong.onlinebanking.customer.controller.dto.res;

import org.sampong.onlinebanking._common.enumerate.IdType;

import java.time.LocalDateTime;

public record CustomerIdentificationResponse(
        Long id,
        String idNumber,
        LocalDateTime issueDate,
        LocalDateTime expireDate,
        IdType idType,
        CustomerResponse customer
) {
}
