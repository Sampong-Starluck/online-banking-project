package org.sampong.onlinebanking.user.repository;

import org.sampong.onlinebanking._common.base.BaseRepository;
import org.sampong.onlinebanking.user.model.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends BaseRepository<Role> {
    Optional<Role> findByRoleNameAndStatusTrue(String name);

    @Query(value = "select r from Role r where r.id in (:id) and r.status = true")
    List<Role> findAllByRoleIds(@Param("id") List<Long> id);
}
