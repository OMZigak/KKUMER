package org.kkumulkkum.server.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AuthErrorCode implements DefaultErrorCode {
    // 400 Bad Request
    INVALID_PROVIDER(HttpStatus.BAD_REQUEST, 40091, "유효하지 않은 소셜 플랫폼입니다."),
    INVALID_APPLE_PUBLIC_KEY(HttpStatus.BAD_REQUEST, 40092,"유효하지 않은 Apple Public Key입니다."),
    CREATE_PUBLIC_KEY_EXCEPTION(HttpStatus.BAD_REQUEST, 40093,"Apple public key 생성에 문제가 발생했습니다."),
    INVALID_APPLE_IDENTITY_TOKEN(HttpStatus.BAD_REQUEST, 40094, "Apple Identity Token 형식이 올바르지 않습니다."),
    EXPIRED_APPLE_IDENTITY_TOKEN(HttpStatus.BAD_REQUEST, 40495, "Apple Identity Token 유효기간이 만료됐습니다."),

    // 401 Unauthorized
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, 40190, "인증되지 않은 사용자입니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, 40191, "액세스 토큰의 형식이 올바르지 않습니다."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, 40192, "액세스 토큰이 만료되었습니다."),
    UNSUPPORTED_TOKEN(HttpStatus.UNAUTHORIZED, 40193, "지원하지 않는 토큰 형식입니다."),
    EMPTY_TOKEN(HttpStatus.UNAUTHORIZED, 40194, "토큰이 제공되지 않았습니다."),
    MISMATCH_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, 40195, "리프레시 토큰이 일치하지 않습니다."),
    UNKNOWN_TOKEN(HttpStatus.UNAUTHORIZED, 40196, "알 수 없는 토큰입니다."),

    // 404 Not Found
    NOT_FOUND_REFRESH_TOKEN(HttpStatus.NOT_FOUND, 40490, "존재하지 않는 리프레시 토큰입니다."),
    ;

    private HttpStatus httpStatus;
    private int code;
    private String message;
}
