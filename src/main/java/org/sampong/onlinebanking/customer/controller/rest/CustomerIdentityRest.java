package org.sampong.onlinebanking.customer.controller.rest;

import org.sampong.onlinebanking._common.base.rest.BaseRest;
import org.sampong.onlinebanking._common.constant.AppConstants;
import org.sampong.onlinebanking.customer.controller.dto.req.CustomerIdentificationRequest;
import org.sampong.onlinebanking.customer.controller.dto.res.CustomerIdentificationResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(AppConstants.WEB_PATH+"/customer-identification")
public interface CustomerIdentityRest extends BaseRest<CustomerIdentificationRequest, CustomerIdentificationResponse> {
}
