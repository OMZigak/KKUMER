package org.kkumulkkum.server.service.promise;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.repository.PromiseRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PromiseRemover {

    private final PromiseRepository promiseRepository;

    public void deleteById(final Long promiseId) {
        promiseRepository.deleteById(promiseId);
    }
}