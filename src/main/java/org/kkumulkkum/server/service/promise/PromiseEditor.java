package org.kkumulkkum.server.service.promise;

import org.kkumulkkum.server.domain.Promise;
import org.springframework.stereotype.Component;

@Component
public class PromiseEditor {

    public void completePromise(final Promise promise) {
        promise.complete();
    }
}
