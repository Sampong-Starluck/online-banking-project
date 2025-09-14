package org.sampong.onlinebanking.user.controller;

import lombok.RequiredArgsConstructor;
import org.sampong.onlinebanking._common.base.res.BaseResponse;
import org.sampong.onlinebanking._common.base.res.ObjectResponse;
import org.sampong.onlinebanking._common.constant.AppConstants;
import org.sampong.onlinebanking.user.model.Role;
import org.sampong.onlinebanking.user.service.RoleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstants.WEB_PATH +"/role")
@PreAuthorize("hasAuthority('ADMIN')")
public class RoleController {
    private final RoleService service;

    @GetMapping("/{id}")
    ObjectResponse<Role> findById(@PathVariable Long id){
        return service.findById(id).map(BaseResponse::success).orElse(BaseResponse.error("Role not found"));
    }

    @PostMapping
    ObjectResponse<Role> save(@RequestBody Role role){
        return BaseResponse.success(service.addNew(role));
    }

    @PutMapping
    ObjectResponse<Role> update(@RequestBody Role role){
        return BaseResponse.success(service.updateObj(role));
    }
}
