package org.kkumulkkum.server.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.common.exception.code.AuthErrorCode;

@Getter
@RequiredArgsConstructor
public class AuthException extends RuntimeException {
    private final AuthErrorCode errorCode;
}
