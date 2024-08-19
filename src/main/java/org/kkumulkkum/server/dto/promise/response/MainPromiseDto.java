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
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime time,
        String placeName
) {
    public static MainPromiseDto from(Promise promise) {
        int dday = (int) ChronoUnit.DAYS.between(promise.getTime().toLocalDate(), LocalDateTime.now().toLocalDate());
        return new MainPromiseDto(
                promise.getId(),
                promise.getName(),
                promise.getMeeting().getName(),
                promise.getDressUpLevel().getContent(),
                dday,
                promise.getTime(),
                promise.getPlaceName()
        );
    }
}
