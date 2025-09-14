package org.sampong.onlinebanking.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.sampong.onlinebanking._common.base.res.BaseResponse;
import org.sampong.onlinebanking._common.constant.AppConstants;
import org.sampong.onlinebanking.user.controller.dto.request.ForgetPasswordRequest;
import org.sampong.onlinebanking.user.controller.dto.request.JwtRequest;
import org.sampong.onlinebanking.user.controller.dto.response.JwtResponse;
import org.sampong.onlinebanking.user.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstants.WEB_PATH +"/auth")
public class AuthController {
    private final UserService service;

    @PostMapping("/login")
    BaseResponse<JwtResponse> login(@RequestBody JwtRequest request) throws JsonProcessingException {
        return BaseResponse.success(service.login(request));
    }

    @PostMapping("/forget-password")
    BaseResponse<JwtResponse> forgetPassword(@RequestBody ForgetPasswordRequest request) throws JsonProcessingException {
        return BaseResponse.success(service.forgetPassword(request));
    }
}

