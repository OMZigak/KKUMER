package org.kkumulkkum.server.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ParticipantErrorCode implements DefaultErrorCode{
    // 400 BAD_REQUEST
    NOT_JOINED_PROMISE(HttpStatus.BAD_REQUEST, 40040, "참여하지 않은 약속입니다."),
    INVALID_STATE(HttpStatus.BAD_REQUEST, 40041, "유효하지 않은 상태 변경입니다."),
    ;

    private HttpStatus httpStatus;
    private int code;
    private String message;
}
