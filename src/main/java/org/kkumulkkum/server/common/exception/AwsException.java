package org.kkumulkkum.server.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.common.exception.code.AwsErrorCode;

@Getter
@RequiredArgsConstructor
public class AwsException extends RuntimeException {
    private final AwsErrorCode errorCode;
}
