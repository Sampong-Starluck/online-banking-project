package org.sampong.onlinebanking._common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
public class CustomException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    private final int status;
    private final String error;

    public CustomException(HttpStatus status, String message) {
        super(message);
        this.status = status.value();
        this.error = status.getReasonPhrase();
    }

    // New ctor that preserves the cause (used by your aspect)
    public CustomException(HttpStatus status, String message, Throwable cause) {
        super(message, cause);
        this.status = status.value();
        this.error = status.getReasonPhrase();
    }

    // convenience if you need HttpStatus back
    public HttpStatus getHttpStatus() {
        return HttpStatus.resolve(status);
    }
}