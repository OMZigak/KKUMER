package org.kkumulkkum.server.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.exception.code.FirebaseErrorCode;

@Getter
@RequiredArgsConstructor
public class FirebaseException extends RuntimeException {
    private final FirebaseErrorCode errorCode;
}
