package org.kkumulkkum.server.common.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum PromiseErrorCode implements DefaultErrorCode {
    // 400 BAD_REQUEST
    NOT_PAST_DUE(HttpStatus.BAD_REQUEST, 40050, "약속 시간이 지나지 않았습니다."),
    NOT_ARRIVED_PARTICIPANT_EXISTS(HttpStatus.BAD_REQUEST, 40051, "도착하지 않은 참여자가 있습니다."),
    // 404 NOT_FOUND
    NOT_FOUND_PROMISE(HttpStatus.NOT_FOUND, 40450, "약속을 찾을 수 없습니다."),
    ;

    private HttpStatus httpStatus;
    private int code;
    private String message;
}
