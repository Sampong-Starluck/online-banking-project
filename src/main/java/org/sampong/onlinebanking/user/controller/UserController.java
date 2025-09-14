package org.sampong.onlinebanking.user.controller;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.sampong.onlinebanking._common.base.request.PageRequest;
import org.sampong.onlinebanking._common.base.res.BaseResponse;
import org.sampong.onlinebanking._common.base.res.ObjectResponse;
import org.sampong.onlinebanking._common.base.res.PageResponse;
import org.sampong.onlinebanking._common.constant.AppConstants;
import org.sampong.onlinebanking.user.controller.dto.request.CreateUserRequest;
import org.sampong.onlinebanking.user.controller.dto.request.UserPageRequest;
import org.sampong.onlinebanking.user.controller.dto.response.UserResponse;
import org.sampong.onlinebanking.user.controller.mapper.UserRestMapper;
import org.sampong.onlinebanking.user.model.Users;
import org.sampong.onlinebanking.user.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstants.WEB_PATH +"/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    private final UserService service;
    private final UserRestMapper mapper;

    @PostMapping("/register")
    ObjectResponse<UserResponse> register(@RequestBody CreateUserRequest request) {
        return BaseResponse.success(mapper.toResponse(service.register(request)));
    }

    @GetMapping("/list")
    PageResponse<UserResponse> list(UserPageRequest pageRequest) {
        var userPage = service.findAllUsers(pageRequest).map(mapper::toResponse);
        return BaseResponse.paginated(userPage);
    }
}
