package org.kkumulkkum.server.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum PromiseErrorCode {
    // 400 Bad Request
    NOT_PAST_DUE(HttpStatus.BAD_REQUEST, 40010, "약속 시간이 지나지 않았습니다."),
    NOT_ARRIVED_PARTICIPANT_EXISTS(HttpStatus.BAD_REQUEST, 40020, "도착하지 않은 참여자가 있습니다."),
    // 404 Not Found
    NOT_FOUND_PROMISE(HttpStatus.NOT_FOUND, 40430, "약속을 찾을 수 없습니다."),
    ;

    private HttpStatus httpStatus;
    private int code;
    private String message;
}
