package org.kkumulkkum.server.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MeetingErrorCode implements DefaultErrorCode {
    // 403 Forbidden
    NOT_JOINED_MEETING(HttpStatus.FORBIDDEN, 40310, "참여하지 않은 모임입니다."),
    // 404 Not Found
    NOT_FOUND_MEETING(HttpStatus.NOT_FOUND, 40420, "모임을 찾을 수 없습니다."),
    // 409 Conflict
    ALREADY_JOINED(HttpStatus.CONFLICT, 40910, "이미 참여한 모임입니다."),
    ;

    private HttpStatus httpStatus;
    private int code;
    private String message;
}
