package org.kkumulkkum.server.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.common.exception.code.UserErrorCode;

@Getter
@RequiredArgsConstructor
public class UserException extends RuntimeException {
    private final UserErrorCode errorCode;
}
