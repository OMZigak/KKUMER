package org.kkumulkkum.server.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AwsErrorCode implements DefaultErrorCode {
    // 400 Bad Request
    INVALID_IMAGE_EXTENSION(HttpStatus.BAD_REQUEST, 40080, "이미지 확장자는 jpg, png, webp만 가능합니다."),
    IMAGE_SIZE_EXCEEDED(HttpStatus.BAD_REQUEST,40081, "이미지 사이즈는 5MB를 넘을 수 없습니다."),

    // 404 Not Found
    NOT_FOUND_IMAGE(HttpStatus.NOT_FOUND, 40480, "삭제할 이미지를 찾을 수 없습니다."),
    ;

    private HttpStatus httpStatus;
    private int code;
    private String message;
}
