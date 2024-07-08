package org.kkumulkkum.server.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.exception.code.UserErrorCode;

@Getter
@RequiredArgsConstructor
public class UserException extends RuntimeException {
    private final UserErrorCode errorCode;
}
