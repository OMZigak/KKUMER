package org.kkumulkkum.server.api.promise.dto.response;

import org.kkumulkkum.server.domain.promise.Promise;

import java.util.List;

public record MainPromisesDto(
        List<MainPromiseDto> promises
) {
    public static MainPromisesDto from(List<Promise> promises) {
        return new MainPromisesDto(
                promises.stream()
                        .map(MainPromiseDto::from)
                        .toList()
        );
    }
}
