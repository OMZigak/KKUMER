package org.kkumulkkum.server.dto.promise.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.kkumulkkum.server.domain.Promise;

import java.time.LocalDateTime;

public record PromiseDto(
        boolean isParticipant,
        Long promiseId,
        String promiseName,
        String placeName,
        String address,
        String roadAddress,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime time,
        String dressUpLevel,
        String penalty
) {
    public static PromiseDto from(Promise promise, boolean isParticipant) {
        return new PromiseDto(
                isParticipant,
                promise.getId(),
                promise.getName(),
                promise.getPlaceName(),
                promise.getAddress(),
                promise.getRoadAddress(),
                promise.getTime(),
                promise.getDressUpLevel().getContent(),
                promise.getPenalty()
        );
    }
}
