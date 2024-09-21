package org.kkumulkkum.server.common.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BusinessErrorCode implements DefaultErrorCode {
    // 400 BAD_REQUEST
    BAD_REQUEST(HttpStatus.BAD_REQUEST,40000, "잘못된 요청입니다."),
    INVALID_ARGUMENTS(HttpStatus.BAD_REQUEST, 40001, "인자의 형식이 올바르지 않습니다."),
    PAYLOAD_TOO_LARGE(HttpStatus.BAD_REQUEST,40002,"최대 업로드 크기를 초과했습니다."),
    MISSING_REQUIRED_PARAM(HttpStatus.BAD_REQUEST,40003,"필수 파라미터가 누락되었습니다."),
    MISSING_REQUIRED_HEADER(HttpStatus.BAD_REQUEST,40004,"필수 헤더가 누락되었습니다."),
    // 404 NOT_FOUND
    NOT_FOUND(HttpStatus.NOT_FOUND,40400, "요청한 정보를 찾을 수 없습니다."),
    NOT_FOUND_END_POINT(HttpStatus.NOT_FOUND,40401, "요청한 엔드포인트를 찾을 수 없습니다."),
    // 405 METHOD_NOT_ALLOWED
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED,40500, "지원하지 않는 메소드입니다."),
    // 500 INTERNAL_SEVER_ERROR
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,50000, "서버 내부 오류입니다."),
    DISCORD_WEBHOOK_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,50001,"디스코드 웹훅 전송에 실패했습니다."),
    ;

    private HttpStatus httpStatus;
    private int code;
    private String message;
}
