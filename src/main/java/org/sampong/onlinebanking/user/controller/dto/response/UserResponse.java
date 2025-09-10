package org.sampong.onlinebanking.user.controller.dto.response;

import org.sampong.onlinebanking.user.model.Role;

import java.util.List;

public record UserResponse (
        Long id,
        String username,
        String phoneNumber,
        String familyName,
        String givenName,
        String email,
        List<Role> Role
) {

}
