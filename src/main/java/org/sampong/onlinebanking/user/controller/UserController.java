package org.sampong.onlinebanking.user.controller;

import lombok.RequiredArgsConstructor;
import org.sampong.onlinebanking._common.base.BaseResponse;
import org.sampong.onlinebanking._common.constant.AppConstants;
import org.sampong.onlinebanking.user.controller.dto.request.CreateUserRequest;
import org.sampong.onlinebanking.user.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstants.WEB_PATH +"/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    private final UserService service;
    private final BaseResponse response;

    @PostMapping("/register")
    Map<String, Object> register(@RequestBody CreateUserRequest request) {
        return response.response(service.register(request));
    }
}
