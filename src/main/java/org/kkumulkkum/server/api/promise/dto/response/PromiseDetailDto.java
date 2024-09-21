package org.kkumulkkum.server.api.promise.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.kkumulkkum.server.domain.promise.Promise;

import java.time.LocalDateTime;

public record PromiseDetailDto(
        boolean isParticipant,
        Long promiseId,
        String promiseName,
        String placeName,
        Double x,
        Double y,
        String address,
        String roadAddress,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime time,
        String dressUpLevel,
        String penalty
) {
    public static PromiseDetailDto from(Promise promise, boolean isParticipant) {
        return new PromiseDetailDto(
                isParticipant,
                promise.getId(),
                promise.getName(),
                promise.getPlaceName(),
                promise.getX(),
                promise.getY(),
                promise.getAddress(),
                promise.getRoadAddress(),
                promise.getTime(),
                promise.getDressUpLevel().getContent(),
                promise.getPenalty()
        );
    }
}
