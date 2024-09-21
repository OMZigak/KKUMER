package org.kkumulkkum.server.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.common.exception.code.FirebaseErrorCode;

@Getter
@RequiredArgsConstructor
public class FirebaseException extends RuntimeException {
    private final FirebaseErrorCode errorCode;
}
