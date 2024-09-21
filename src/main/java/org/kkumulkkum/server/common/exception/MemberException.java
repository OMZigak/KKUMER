package org.kkumulkkum.server.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.common.exception.code.MemberErrorCode;

@Getter
@RequiredArgsConstructor
public class MemberException extends RuntimeException {
    private final MemberErrorCode errorCode;
}
