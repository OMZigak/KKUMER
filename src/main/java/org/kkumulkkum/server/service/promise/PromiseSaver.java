package org.kkumulkkum.server.service.promise;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.domain.Promise;
import org.kkumulkkum.server.repository.PromiseRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PromiseSaver {

    private final PromiseRepository promiseRepository;

    public void save(final Promise promise) {
        promiseRepository.save(promise);
    }
}
