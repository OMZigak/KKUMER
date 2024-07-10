package org.kkumulkkum.server.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.exception.code.OpenApiErrorCode;

@Getter
@RequiredArgsConstructor
public class OpenApiException extends RuntimeException {
    private final OpenApiErrorCode errorCode;
}
