package org.kkumulkkum.server.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum PromiseErrorCode {
    //404 Not Found
    NOT_FOUND_PROMISE(HttpStatus.NOT_FOUND, 40430, "약속을 찾을 수 없습니다."),
    ;

    private HttpStatus httpStatus;
    private int code;
    private String message;
}