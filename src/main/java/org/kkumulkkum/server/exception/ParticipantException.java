package org.kkumulkkum.server.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.exception.code.ParticipantErrorCode;

@Getter
@RequiredArgsConstructor
public class ParticipantException extends RuntimeException {
    private final ParticipantErrorCode errorCode;
}
