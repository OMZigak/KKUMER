package org.kkumulkkum.server.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MeetingErrorCode implements DefaultErrorCode {
    // 403 FORBIDDEN
    NOT_JOINED_MEETING(HttpStatus.FORBIDDEN, 40330, "참여하지 않은 모임입니다."),
    // 404 NOT_FOUND
    NOT_FOUND_MEETING(HttpStatus.NOT_FOUND, 40430, "모임을 찾을 수 없습니다."),
    // 409 CONFLICT
    ALREADY_JOINED(HttpStatus.CONFLICT, 40930, "이미 참여한 모임입니다."),
    ;

    private HttpStatus httpStatus;
    private int code;
    private String message;
}
