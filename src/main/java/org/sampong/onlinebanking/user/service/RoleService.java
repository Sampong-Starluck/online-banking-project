package org.sampong.onlinebanking.user.service;

import org.sampong.onlinebanking._common.base.service.BaseService;
import org.sampong.onlinebanking.user.controller.dto.request.RolePageReq;
import org.sampong.onlinebanking.user.model.Role;

import java.util.List;

public interface RoleService extends BaseService<Role, RolePageReq> {

    List<Role> findAllByIds(List<Long> ids);
}
