package org.sampong.onlinebanking.user.controller.dto.request;

import lombok.Data;

@Data
public class ForgetPasswordRequest {
    private String email;
    private String password;
}
