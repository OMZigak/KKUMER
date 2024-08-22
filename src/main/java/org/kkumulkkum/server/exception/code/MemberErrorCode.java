package org.kkumulkkum.server.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements DefaultErrorCode {
    // 403 FORBIDDEN
    NOT_JOINED_MEMBER(HttpStatus.FORBIDDEN, 40340, "모임에 참여하지 않은 회원입니다."),
    // 404 NOT_FOUND
    NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND, 40440, "멤버를 찾을 수 없습니다.")
    ;

    private HttpStatus httpStatus;
    private int code;
    private String message;
}
