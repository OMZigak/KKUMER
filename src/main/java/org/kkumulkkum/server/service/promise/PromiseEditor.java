package org.kkumulkkum.server.service.promise;

import org.kkumulkkum.server.domain.promise.Promise;
import org.kkumulkkum.server.dto.promise.PromiseCreateDto;
import org.springframework.stereotype.Component;

@Component
public class PromiseEditor {

    public void completePromise(final Promise promise) {
        promise.complete();
    }

    public void updatePromise(
            final Promise promise,
            final PromiseCreateDto updatePromiseDto
    ) {
        promise.updatePromise(
                updatePromiseDto.name(),
                updatePromiseDto.placeName(),
                updatePromiseDto.x(),
                updatePromiseDto.y(),
                updatePromiseDto.address(),
                updatePromiseDto.roadAddress(),
                updatePromiseDto.time(),
                updatePromiseDto.dressUpLevel(),
                updatePromiseDto.penalty()
        );
    }
}
