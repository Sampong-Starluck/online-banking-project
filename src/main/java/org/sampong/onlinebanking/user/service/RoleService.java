package org.sampong.onlinebanking.user.service;

import org.sampong.onlinebanking._common.base.BaseService;
import org.sampong.onlinebanking.user.model.Role;

import java.util.List;

public interface RoleService extends BaseService<Role> {

    List<Role> findAllByIds(List<Long> ids);
}
