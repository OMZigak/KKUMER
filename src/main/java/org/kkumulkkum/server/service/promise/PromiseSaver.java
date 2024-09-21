package org.kkumulkkum.server.service.promise;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.domain.promise.Promise;
import org.kkumulkkum.server.domain.promise.repository.PromiseRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PromiseSaver {

    private final PromiseRepository promiseRepository;

    public void save(final Promise promise) {
        promiseRepository.save(promise);
    }
}
