package org.kkumulkkum.server.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AuthErrorCode implements DefaultErrorCode {
    // 400 BAD_REQUEST
    INVALID_PROVIDER(HttpStatus.BAD_REQUEST, 40010, "유효하지 않은 소셜 플랫폼입니다."),
    INVALID_APPLE_PUBLIC_KEY(HttpStatus.BAD_REQUEST, 40011,"유효하지 않은 Apple Public Key입니다."),
    CREATE_PUBLIC_KEY_EXCEPTION(HttpStatus.BAD_REQUEST, 40012,"Apple public key 생성에 문제가 발생했습니다."),
    INVALID_APPLE_IDENTITY_TOKEN(HttpStatus.BAD_REQUEST, 40013, "Apple Identity Token 형식이 올바르지 않습니다."),
    EXPIRED_APPLE_IDENTITY_TOKEN(HttpStatus.BAD_REQUEST, 40014, "Apple Identity Token 유효기간이 만료됐습니다."),
    APPLE_REVOKE_FAILED(HttpStatus.BAD_REQUEST, 40015,"Apple 회원 탈퇴에 실패했습니다."),
    APPLE_TOKEN_REQUEST_FAILED(HttpStatus.BAD_REQUEST, 40016, "Apple 토큰 요청에 실패했습니다."),
    // 401 UNAUTHORIZED
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, 40110, "인증되지 않은 사용자입니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, 40111, "올바르지 않은 토큰입니다."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, 40112, "액세스 토큰이 만료되었습니다."),
    UNSUPPORTED_TOKEN(HttpStatus.UNAUTHORIZED, 40113, "지원하지 않는 토큰 형식입니다."),
    EMPTY_TOKEN(HttpStatus.UNAUTHORIZED, 40114, "토큰이 제공되지 않았습니다."),
    MISMATCH_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, 40115, "리프레시 토큰이 일치하지 않습니다."),
    UNKNOWN_TOKEN(HttpStatus.UNAUTHORIZED, 40116, "알 수 없는 토큰입니다."),
    // 404 NOT_FOUND
    NOT_FOUND_REFRESH_TOKEN(HttpStatus.NOT_FOUND, 40410, "존재하지 않는 리프레시 토큰입니다."),
    ;

    private HttpStatus httpStatus;
    private int code;
    private String message;
}
