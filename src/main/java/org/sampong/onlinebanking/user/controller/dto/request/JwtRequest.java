package org.sampong.onlinebanking.user.controller.dto.request;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
}
