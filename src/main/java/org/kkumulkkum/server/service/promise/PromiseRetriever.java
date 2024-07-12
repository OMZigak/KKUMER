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

    public Promise findNextPromiseByUserId(Long userId, LocalDateTime startOfDay, LocalDateTime startOfNextDay) {
        Page<Promise> promisePage = promiseRepository.findNextPromiseByUserId(userId, startOfDay, startOfNextDay, PageRequest.of(0,1));

        return promisePage.stream()
                .findFirst()
                .orElseThrow(() -> new PromiseException(PromiseErrorCode.NOT_FOUND_PROMISE));
    }
}
