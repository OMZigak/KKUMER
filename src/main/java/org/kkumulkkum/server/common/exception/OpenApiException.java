package org.kkumulkkum.server.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.common.exception.code.OpenApiErrorCode;

@Getter
@RequiredArgsConstructor
public class OpenApiException extends RuntimeException {
    private final OpenApiErrorCode errorCode;
}
