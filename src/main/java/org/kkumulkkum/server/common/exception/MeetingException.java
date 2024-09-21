package org.kkumulkkum.server.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.common.exception.code.MeetingErrorCode;

@Getter
@RequiredArgsConstructor
public class MeetingException extends RuntimeException {
    private final MeetingErrorCode errorCode;
}