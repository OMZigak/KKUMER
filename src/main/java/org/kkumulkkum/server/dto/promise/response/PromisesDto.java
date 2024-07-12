package org.kkumulkkum.server.dto.promise.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.kkumulkkum.server.domain.Promise;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public record PromisesDto(
        List<PromiseDto> promises
) {
    public static PromisesDto of(List<Promise> promises, Boolean done) {
        return new PromisesDto(
                promises.stream()
                        .filter(promise -> done == null || done.equals(promise.isCompleted()))
                        .map(PromisesDto.PromiseDto::from)
                        .toList()
        );
    }

    public record PromiseDto(
            Long promiseId,
            String name,
            int dDay,
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd")
            LocalDateTime date,
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "a h:mm", locale = "en")
            LocalDateTime time,
            String placeName
    ) {
        public static PromiseDto from(Promise promise) {
            return new PromiseDto(
                    promise.getId(),
                    promise.getName(),
                    //약속 시간이랑 지금 시간이랑 비교해서 dDay 계산
                    (int) ChronoUnit.DAYS.between(LocalDateTime.now(), promise.getTime()),
                    promise.getTime(),
                    promise.getTime(),
                    promise.getPlaceName()
            );
        }
    }
}
