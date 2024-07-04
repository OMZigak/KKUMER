package org.kkumulkkum.server.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.exception.code.MeetingErrorCode;

@Getter
@RequiredArgsConstructor
public class MeetingException extends RuntimeException {
    private final MeetingErrorCode errorCode;
}