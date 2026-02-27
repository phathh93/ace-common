package com.ace.exception;

import com.ace.AceStatusCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AceException extends RuntimeException {
    private final AceStatusCode status;

    public AceException(AceStatusCode status) {
        super(status.getMessage());
        this.status = status;
    }

    public AceException(AceStatusCode status, String message) {
        super(message);
        this.status = status;
    }

    public AceException(AceStatusCode status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
    }

    public HttpStatus getHttpStatus() {
        return status.getHttpStatus();
    }
}
