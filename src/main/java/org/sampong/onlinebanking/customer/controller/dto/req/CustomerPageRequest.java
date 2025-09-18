package org.sampong.onlinebanking.customer.controller.dto.req;

import lombok.Getter;
import lombok.Setter;
import org.sampong.onlinebanking._common.base.request.PageRequest;

@Getter
@Setter
public class CustomerPageRequest extends PageRequest {
    String customerName;
}
