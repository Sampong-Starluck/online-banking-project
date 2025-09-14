package org.sampong.onlinebanking.user.controller;

import lombok.RequiredArgsConstructor;
import org.sampong.onlinebanking._common.base.res.BaseResponse;
import org.sampong.onlinebanking._common.constant.AppConstants;
import org.sampong.onlinebanking.user.controller.dto.request.CreateUserRequest;
import org.sampong.onlinebanking.user.model.Users;
import org.sampong.onlinebanking.user.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstants.WEB_PATH +"/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    private final UserService service;

    @PostMapping("/register")
    BaseResponse <Users> register(@RequestBody CreateUserRequest request) {
        return BaseResponse.success(service.register(request));
    }
}
