package org.sampong.onlinebanking.account.controller.rest;

import org.sampong.onlinebanking._common.base.res.ObjectResponse;
import org.sampong.onlinebanking._common.base.rest.BaseRest;
import org.sampong.onlinebanking._common.constant.AppConstants;
import org.sampong.onlinebanking.account.controller.dto.request.AccountRequest;
import org.sampong.onlinebanking.account.controller.dto.res.AccountResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(AppConstants.WEB_PATH+"/account")
@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
public interface AccountRest extends BaseRest<AccountRequest, AccountResponse> {
    @GetMapping("/customer/{id}")
    ObjectResponse<List<AccountResponse>> getAllAccountByCustomerId(@PathVariable Long id);
}
