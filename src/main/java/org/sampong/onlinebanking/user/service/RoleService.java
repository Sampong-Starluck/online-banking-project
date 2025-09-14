package org.sampong.onlinebanking.user.service;

import org.sampong.onlinebanking._common.base.service.BasePageService;
import org.sampong.onlinebanking.user.controller.dto.request.RolePageReq;
import org.sampong.onlinebanking.user.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService extends BasePageService<Role, RolePageReq> {

    Optional<Role> findByName(String name);
    List<Role> findAllByIds(List<Long> ids);
}
