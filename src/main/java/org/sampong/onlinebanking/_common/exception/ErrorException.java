package org.sampong.onlinebanking._common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class ErrorException {

    // Getters and Setters
    private Integer status;
    private String error;
    private String message;

    public ErrorException() {
        this.status = HttpStatus.UNAUTHORIZED.value();
        this.error = HttpStatus.UNAUTHORIZED.name();
    }

}
