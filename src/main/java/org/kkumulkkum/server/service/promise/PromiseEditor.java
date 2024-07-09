package org.kkumulkkum.server.service.promise;

import org.kkumulkkum.server.domain.Promise;
import org.springframework.stereotype.Component;

@Component
public class PromiseEditor {

    public void completePromise(Promise promise) {
        promise.complete();
    }
}
