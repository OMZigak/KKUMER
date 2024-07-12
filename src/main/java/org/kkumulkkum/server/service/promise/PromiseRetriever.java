package org.kkumulkkum.server.service.promise;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.domain.Promise;
import org.kkumulkkum.server.exception.PromiseException;
import org.kkumulkkum.server.exception.code.PromiseErrorCode;
import org.kkumulkkum.server.repository.PromiseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PromiseRetriever {

    private final PromiseRepository promiseRepository;

    public List<Promise> findAllByMeetingId(Long meetingId) {
        return promiseRepository.findAllByMeetingId(meetingId);
    }

    public Promise findById(Long id) {
        return promiseRepository.findById(id)
                .orElseThrow(() -> new PromiseException(PromiseErrorCode.NOT_FOUND_PROMISE));
    }

    public List<Promise> findNextPromiseByUserId(Long userId, LocalDateTime startOfDay, LocalDateTime startOfNextDay) {
        return promiseRepository.findNextPromiseByUserId(userId, startOfDay, startOfNextDay, PageRequest.of(0,1));

    }

    public List<Promise> findUpcomingPromisesExcludingNext(Long userId, Promise nextPromise, int limit) {
        Page<Promise> promisePage = promiseRepository.findUpcomingPromisesExcludingNext(userId, nextPromise.getId(), PageRequest.of(0, limit));
        return promisePage.stream().collect(Collectors.toList());
    }

    public List<Promise> findUpcomingPromises(Long userId, int limit) {
        Page<Promise> promisePage = promiseRepository.findUpcomingPromises(userId, PageRequest.of(0, limit));
        return promisePage.stream().collect(Collectors.toList());
    }
}
