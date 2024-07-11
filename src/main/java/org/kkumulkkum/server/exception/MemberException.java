package org.kkumulkkum.server.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.exception.code.MemberErrorCode;

@Getter
@RequiredArgsConstructor
public class MemberException extends RuntimeException {
    private final MemberErrorCode errorCode;
}
