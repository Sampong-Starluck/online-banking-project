package org.sampong.onlinebanking.customer.controller.dto.req;

import org.sampong.onlinebanking._common.base.request.ObjectIdRequest;
import org.sampong.onlinebanking._common.enumerate.IdType;
import org.sampong.onlinebanking.customer.model.CustomerIdentification;

import java.time.LocalDateTime;

/**
 * DTO for {@link CustomerIdentification}
 */
public record CustomerIdentificationRequest(Long id, String idNumber, LocalDateTime issueDate, LocalDateTime expireDate,
                                            IdType idType, ObjectIdRequest customer) {
}