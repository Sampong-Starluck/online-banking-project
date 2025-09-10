package org.sampong.onlinebanking.user.repository;

import org.sampong.onlinebanking._common.base.BaseRepository;
import org.sampong.onlinebanking.user.model.Users;
import org.sampong.onlinebanking.user.model.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<Users> {
    @Query(value = "select u from Users u where u.username = :username and u.status = true")
    Users findByUsername(String username);
    @Query(value = "select u from Users u where u.email = :email and u.status = true")
    Users findByEmail(String email);

    Boolean existsByUsernameAndStatusTrue(String username);
    Boolean existsByEmailAndStatusTrue(String email);
}