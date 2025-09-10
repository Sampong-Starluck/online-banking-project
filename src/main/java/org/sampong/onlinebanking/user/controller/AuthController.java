package org.sampong.onlinebanking.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.sampong.onlinebanking._common.base.BaseResponse;
import org.sampong.onlinebanking._common.constant.AppConstants;
import org.sampong.onlinebanking.user.controller.dto.request.ForgetPasswordRequest;
import org.sampong.onlinebanking.user.controller.dto.request.JwtRequest;
import org.sampong.onlinebanking.user.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstants.WEB_PATH +"/auth")
public class AuthController {
    private final UserService service;
    private final BaseResponse response;

    @PostMapping("/login")
    Map<String, Object> login(@RequestBody JwtRequest request) throws JsonProcessingException {
        return response.response(service.login(request));
    }

    @PostMapping("/forget-password")
    Map<String, Object> forgetPassword(@RequestBody ForgetPasswordRequest request) throws JsonProcessingException {
        return response.response(service.forgetPassword(request));
    }
}

