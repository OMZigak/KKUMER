package org.kkumulkkum.server.exception.code;

import org.springframework.http.HttpStatus;

public interface DefaultErrorCode {
    HttpStatus getHttpStatus();
    int getCode();
    String getMessage();
}