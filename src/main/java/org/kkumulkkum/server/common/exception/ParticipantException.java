package org.kkumulkkum.server.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.common.exception.code.ParticipantErrorCode;

@Getter
@RequiredArgsConstructor
public class ParticipantException extends RuntimeException {
    private final ParticipantErrorCode errorCode;
}
