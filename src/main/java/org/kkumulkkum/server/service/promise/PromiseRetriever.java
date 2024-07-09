package org.kkumulkkum.server.service.promise;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.domain.Promise;
import org.kkumulkkum.server.repository.PromiseRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PromiseRetriever {

    private final PromiseRepository promiseRepository;

    public List<Promise> findAllByMeetingId(Long meetingId) {
        return promiseRepository.findAllByMeetingId(meetingId);
    }
}
