package org.kkumulkkum.server.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.common.exception.code.PromiseErrorCode;

@Getter
@RequiredArgsConstructor
public class PromiseException extends RuntimeException {
    private final PromiseErrorCode errorCode;
}
