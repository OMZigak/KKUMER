package org.kkumulkkum.server.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.exception.code.PromiseErrorCode;

@Getter
@RequiredArgsConstructor
public class PromiseException extends RuntimeException {
    private final PromiseErrorCode errorCode;
}
