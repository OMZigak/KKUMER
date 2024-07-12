package org.kkumulkkum.server.service.promise;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.domain.Meeting;
import org.kkumulkkum.server.domain.Member;
import org.kkumulkkum.server.domain.Participant;
import org.kkumulkkum.server.domain.Promise;
import org.kkumulkkum.server.dto.promise.PromiseCreateDto;
import org.kkumulkkum.server.dto.promise.response.MainPromiseDto;
import org.kkumulkkum.server.dto.promise.response.PromiseDto;
import org.kkumulkkum.server.dto.promise.response.PromisesDto;
import org.kkumulkkum.server.service.participant.ParticipantSaver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PromiseService {

    private final PromiseSaver promiseSaver;
    private final PromiseRetriever promiseRetriever;
    private final PromiseEditor promiseEditor;
    private final ParticipantSaver participantSaver;
    private final EntityManager entityManager;

    @Transactional
    public Long createPromise(Long userId, Long meetingId, PromiseCreateDto createPromiseDto) {
        Promise promise = Promise.builder()
                .meeting(entityManager.getReference(Meeting.class, meetingId))
                .name(createPromiseDto.name())
                .placeName(createPromiseDto.placeName())
                .x(createPromiseDto.x())
                .y(createPromiseDto.y())
                .address(createPromiseDto.address())
                .roadAddress(createPromiseDto.roadAddress())
                .time(createPromiseDto.time())
                .dressUpLevel(createPromiseDto.dressUpLevel())
                .penalty(createPromiseDto.penalty())
                .build();
        promiseSaver.save(promise);

        createPromiseDto.participants().add(userId);
        participantSaver.saveAll(
                createPromiseDto.participants().stream()
                .map(participantId
                        -> Participant.builder()
                        .promise(promise)
                        .member(entityManager.getReference(Member.class, participantId))
                        .build()).toList()
        );
        return promise.getId();
    }

    @Transactional
    public void completePromise(Long userId, Long promiseId) {
        // TODO: PARTICIPANT 검증
        Promise promise = promiseRetriever.findById(promiseId);
        promiseEditor.completePromise(promise);
    }

    @Transactional(readOnly = true)
    public PromisesDto getPromises(
            final Long meetingId,
            final Boolean done
    ) {
        List<Promise> promises = promiseRetriever.findAllByMeetingId(meetingId);
        return PromisesDto.of(promises, done);
    }

    @Transactional(readOnly = true)
    public PromiseDto getPromise(
            final Long promiseId
    ) {
        Promise promise = promiseRetriever.findById(promiseId);
        return PromiseDto.from(promise);
    }

    @Transactional(readOnly = true)
    public MainPromiseDto getNextPromise(final Long userId) {
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime startOfNextDay = startOfDay.plusDays(1);
        return MainPromiseDto.from(promiseRetriever.findNextPromiseByUserId(userId, startOfDay, startOfNextDay));
    }
}
