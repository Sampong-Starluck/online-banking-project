package org.sampong.onlinebanking.user.controller;

import lombok.RequiredArgsConstructor;
import org.sampong.onlinebanking._common.base.BaseResponse;
import org.sampong.onlinebanking._common.constant.AppConstants;
import org.sampong.onlinebanking.user.model.Role;
import org.sampong.onlinebanking.user.service.RoleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstants.WEB_PATH +"/role")
@PreAuthorize("hasAuthority('ADMIN')")
public class RoleController {
    private final RoleService service;
    private final BaseResponse response;

    @GetMapping("/{id}")
    Map<String,Object> findById(@PathVariable Long id){
        return response.response(service.findById(id));
    }

    @PostMapping
    Map<String,Object> save(@RequestBody Role role){
        return response.response(service.addNew(role));
    }

    @PutMapping
    Map<String,Object> update(@RequestBody Role role){
        return response.response(service.updateObj(role));
    }
}
