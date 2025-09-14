package org.sampong.onlinebanking.user.service.implement;

import lombok.RequiredArgsConstructor;
import org.sampong.onlinebanking._common.exception.CustomException;
import org.sampong.onlinebanking.user.controller.dto.request.RolePageReq;
import org.sampong.onlinebanking.user.model.Role;
import org.sampong.onlinebanking.user.repository.RoleRepository;
import org.sampong.onlinebanking.user.service.RoleService;
import org.sampong.onlinebanking.user.service.mapper.RoleServiceMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImp implements RoleService {
    private final RoleRepository repository;
    private final RoleServiceMapper mapper;

    @Override
    public Optional<Role> findById(Long id) {
        return Optional.ofNullable(repository.findByIdAndStatusTrue(id)).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Role Not Found"));
    }

    @Override
    public Optional<List<Role>> findAllList() {
        return Optional.empty();
    }

    @Override
    public Optional<Role> findByName(String name) {
        return Optional.ofNullable(repository.findByRoleNameAndStatusTrue(name)).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Role Not Found"));
    }

    @Override
    public Role addNew(Role role) {
        return repository.save(role);
    }

    @Override
    public Role updateObj(Role role) {
        var oldRole = findById(role.getId());
        if (oldRole.isEmpty()) {
            throw new CustomException(HttpStatus.NOT_FOUND, "Role Not Found");
        }
        var updateRole = mapper.from(role, oldRole.get());
        return repository.save(updateRole);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Role> findAllByIds(List<Long> ids) {
        return Optional.ofNullable(repository.findAllByRoleIds(ids)).orElseGet(List::of);
    }

    @Override
    public Page<Role> findAllPage(RolePageReq rolePageReq) {
        return null;
    }
}
