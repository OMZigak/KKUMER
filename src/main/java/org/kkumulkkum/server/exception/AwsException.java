package org.kkumulkkum.server.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.exception.code.AwsErrorCode;

@Getter
@RequiredArgsConstructor
public class AwsException extends RuntimeException {
    private final AwsErrorCode errorCode;
}
