package org.kkumulkkum.server.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum FirebaseErrorCode implements DefaultErrorCode {
    // 400 BAD_REQUEST
    FCM_ERROR(HttpStatus.BAD_REQUEST,40070,"fcm 토큰 오류입니다."),
    // 404 NOT_FOUND
    NOT_FOUND_FIREBASE_JSON(HttpStatus.NOT_FOUND, 40470, "FIREBASE JSON을 찾을 수 없습니다."),
    ;

    private HttpStatus httpStatus;
    private int code;
    private String message;
}
