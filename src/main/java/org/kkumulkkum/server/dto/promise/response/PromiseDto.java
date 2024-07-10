package org.kkumulkkum.server.dto.promise.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.kkumulkkum.server.domain.Promise;

import java.time.LocalDateTime;

public record PromiseDto(
        String placeName,
        String address,
        String roadAddress,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "M월 d일 HH:mm")
        LocalDateTime time,
        String dressUpLevel,
        String penalty
) {
    public static PromiseDto from(Promise promise) {
        return new PromiseDto(
                promise.getPlaceName(),
                promise.getAddress(),
                promise.getRoadAddress(),
                promise.getTime(),
                promise.getDressUpLevel().getContent(),
                promise.getPenalty()
        );
    }
}
