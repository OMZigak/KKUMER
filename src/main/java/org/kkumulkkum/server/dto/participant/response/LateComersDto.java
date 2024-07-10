package org.kkumulkkum.server.dto.participant.response;

import org.kkumulkkum.server.domain.Promise;

import java.time.LocalDateTime;
import java.util.List;

public record LateComersDto(
        String penalty,
        Boolean isPastDue,
        List<LateComerDto> lateComers
) {
    public static LateComersDto of(Promise promise, List<LateComerDto> lateComers) {
        boolean isPastDue = promise.getTime().isBefore(LocalDateTime.now());

        return new LateComersDto(
                promise.getPenalty(),
                isPastDue,
                lateComers
        );
    }
}
