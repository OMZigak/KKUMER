package org.kkumulkkum.server.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.common.exception.code.BusinessErrorCode;

@Getter
@RequiredArgsConstructor
public class BusinessException extends RuntimeException {
    private final BusinessErrorCode errorCode;
}