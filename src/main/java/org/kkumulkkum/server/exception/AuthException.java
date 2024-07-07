package org.kkumulkkum.server.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.exception.code.AuthErrorCode;

@Getter
@RequiredArgsConstructor
public class AuthException extends RuntimeException {
    private final AuthErrorCode errorCode;
}
