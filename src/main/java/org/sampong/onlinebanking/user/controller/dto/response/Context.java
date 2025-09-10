package org.sampong.onlinebanking.user.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sampong.onlinebanking._common.enumerate.RoleStatus;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Context {
    private Long id;
    private String username;
    private List<RoleStatus> role;
}
