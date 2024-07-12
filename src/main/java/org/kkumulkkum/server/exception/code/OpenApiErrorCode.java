package org.kkumulkkum.server.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum OpenApiErrorCode implements DefaultErrorCode {
    // 400 BAD_REQUEST
    INVALID_ARGUMENT(HttpStatus.BAD_REQUEST, 40090, "유효하지 않은 인자입니다."),
    // 500 INTERVAL_SERVER_ERROR
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 50090, "외부 API 서버 오류입니다."),
    ;



    private HttpStatus httpStatus;
    private int code;
    private String message;
}
