package org.kkumulkkum.server.dto.promise.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.kkumulkkum.server.domain.Promise;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public record MainPromiseDto(
        Long promiseId,
        String name,
        String meetingName,
        String dressUpLevel,
        int dDay,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd")
        LocalDateTime date,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "a h:mm", locale = "en")
        LocalDateTime time,
        String placeName
) {
    public static MainPromiseDto from(Promise promise) {
        return new MainPromiseDto(
                promise.getId(),
                promise.getName(),
                promise.getMeeting().getName(),
                promise.getDressUpLevel().getContent(),
                (int) ChronoUnit.DAYS.between(LocalDateTime.now(), promise.getTime()),
                promise.getTime(),
                promise.getTime(),
                promise.getPlaceName()
        );
    }
}
