package org.sampong.onlinebanking.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.sampong.onlinebanking._common.base.request.PageRequest;
import org.sampong.onlinebanking.user.controller.dto.request.CreateUserRequest;
import org.sampong.onlinebanking.user.controller.dto.request.ForgetPasswordRequest;
import org.sampong.onlinebanking.user.controller.dto.request.JwtRequest;
import org.sampong.onlinebanking.user.controller.dto.request.UserPageRequest;
import org.sampong.onlinebanking.user.controller.dto.response.JwtResponse;
import org.sampong.onlinebanking.user.model.Users;
import org.springframework.data.domain.Page;

public interface UserService {
    JwtResponse login(JwtRequest jwtRequest) throws JsonProcessingException;
    Users register(CreateUserRequest createUserRequest);
    JwtResponse forgetPassword(ForgetPasswordRequest createUserRequest) throws JsonProcessingException;
    Page<Users> findAllUsers(UserPageRequest pageRequest);
}
