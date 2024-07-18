package org.kkumulkkum.server.service.promise;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.domain.*;
import org.kkumulkkum.server.dto.promise.PromiseCreateDto;
import org.kkumulkkum.server.dto.promise.response.MainPromiseDto;
import org.kkumulkkum.server.dto.promise.response.MainPromisesDto;
import org.kkumulkkum.server.dto.promise.response.PromiseDto;
import org.kkumulkkum.server.dto.promise.response.PromisesDto;
import org.kkumulkkum.server.exception.PromiseException;
import org.kkumulkkum.server.exception.code.PromiseErrorCode;
import org.kkumulkkum.server.service.member.MemberRetreiver;
import org.kkumulkkum.server.service.participant.ParticipantRetriever;
import org.kkumulkkum.server.service.participant.ParticipantSaver;
import org.kkumulkkum.server.service.userInfo.UserInfoRetriever;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
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
    private final ParticipantRetriever participantRetriever;
    private final UserInfoRetriever userInfoRetriever;
    private final EntityManager entityManager;
    private final MemberRetreiver memberRetreiver;

    @Transactional
    public PromiseDto createPromise(
            final Long userId,
            final Long meetingId,
            final PromiseCreateDto createPromiseDto
    ) {
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

        Member member = memberRetreiver.findByMeetingIdAndUserId(meetingId, userId);
        createPromiseDto.participants().add(member.getId());
        participantSaver.saveAll(
                createPromiseDto.participants().stream()
                .map(participantId
                        -> Participant.builder()
                        .promise(promise)
                        .member(entityManager.getReference(Member.class, participantId))
                        .build()).toList()
        );
        return PromiseDto.from(promise);
    }

    @Transactional
    public void completePromise(final Long promiseId) {
        Promise promise = promiseRetriever.findById(promiseId);
        if (promise.getTime().isAfter(LocalDateTime.now())) {
            throw new PromiseException(PromiseErrorCode.NOT_PAST_DUE);
        }

        if (promiseRetriever.existsByArrivedAtIsNull(promiseId)) {
            throw new PromiseException(PromiseErrorCode.NOT_ARRIVED_PARTICIPANT_EXISTS);
        }

        promiseEditor.completePromise(promise);
        List<Participant> participants = participantRetriever.findAllByPromiseId(promiseId);
        participants.forEach(participant -> updateUserInfo(participant, promise.getTime()));
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
        List<Promise> promise = promiseRetriever.findNextPromiseByUserId(userId, startOfDay, startOfNextDay);
        if (promise.isEmpty()) {
            return null;
        }
        return MainPromiseDto.from(promise.get(0));
    }

    @Transactional(readOnly = true)
    public MainPromisesDto getUpcomingPromises(final Long userId) {
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime startOfNextDay = startOfDay.plusDays(1);
        List<Promise> nextPromise = promiseRetriever.findNextPromiseByUserId(userId, startOfDay, startOfNextDay);

        if (!nextPromise.isEmpty()) {
            return MainPromisesDto.from(
                    promiseRetriever.findUpcomingPromisesExcludingNext(userId, nextPromise.get(0), 4)
            );
        }
        return MainPromisesDto.from(promiseRetriever.findUpcomingPromises(userId, 4));
    }

    private void updateUserInfo(
            final Participant participant,
            final LocalDateTime promiseTime
    ) {
        UserInfo userInfo = userInfoRetriever.findByParticipantId(participant.getId());
        userInfo.addPromiseCount();
        if (promiseTime.isBefore(participant.getArrivalAt())) {
            userInfo.addLateCount();
            userInfo.addLateTime(Duration.between(promiseTime, participant.getArrivalAt()).getSeconds());
        } else {
            userInfo.levelUp();
        }
    }

}
