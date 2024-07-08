package org.kkumulkkum.server.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BusinessErrorCode implements DefaultErrorCode {
    // 400 Bad Request
    BAD_REQUEST(HttpStatus.BAD_REQUEST,40000, "잘못된 요청입니다."),
    INVALID_ARGUMENTS(HttpStatus.BAD_REQUEST, 40001, "인자의 형식이 올바르지 않습니다."),
    // 404 Not Found
    NOT_FOUND(HttpStatus.NOT_FOUND,40400, "요청한 정보를 찾을 수 없습니다."),
    NOT_FOUND_END_POINT(HttpStatus.NOT_FOUND,40401, "요청한 엔드포인트를 찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,50000, "서버 내부 오류입니다."),
    ;

    private HttpStatus httpStatus;
    private int code;
    private String message;
}
