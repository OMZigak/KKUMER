package org.kkumulkkum.server.service.participant;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kkumulkkum.server.domain.Member;
import org.kkumulkkum.server.domain.Participant;
import org.kkumulkkum.server.domain.Promise;
import org.kkumulkkum.server.dto.member.response.MemberDto;
import org.kkumulkkum.server.dto.participant.ParticipantStatusUserInfoDto;
import org.kkumulkkum.server.dto.participant.request.PreparationInfoDto;
import org.kkumulkkum.server.dto.participant.response.*;
import org.kkumulkkum.server.exception.ParticipantException;
import org.kkumulkkum.server.exception.code.ParticipantErrorCode;
import org.kkumulkkum.server.external.FcmService;
import org.kkumulkkum.server.external.dto.FcmMessageDto;
import org.kkumulkkum.server.external.enums.FcmContent;
import org.kkumulkkum.server.service.member.MemberRetreiver;
import org.kkumulkkum.server.service.promise.PromiseRetriever;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParticipantService {

    private final ParticipantRetriever participantRetriever;
    private final ParticipantEditor participantEditor;
    private final PromiseRetriever promiseRetriever;
    private final MemberRetreiver memberRetreiver;
    private final ParticipantRemover participantRemover;
    private final FcmService fcmService;

    @Transactional
    public void preparePromise(
            final Long userId,
            final Long promiseId
    ) {
        Participant participant = participantRetriever.findByPromiseIdAndUserId(promiseId, userId);
        if (!validateState(participant, "preparation")) {
            throw new ParticipantException(ParticipantErrorCode.INVALID_STATE);
        }
        participantEditor.preparePromise(participant);

        int preparationCount = participantRetriever.countFirstPreparationByPromiseId(promiseId);
        if (preparationCount == 1) {
            List<String> fcmTokens = participantRetriever.findFcmTokenByPromiseId(promiseId, userId);
            fcmService.sendBulk(fcmTokens, FcmMessageDto.of(FcmContent.FIRST_PREPARATION, promiseId));
        }
    }

    @Transactional
    public void departurePromise(
            final Long userId,
            final Long promiseId
    ) {
        Participant participant = participantRetriever.findByPromiseIdAndUserId(promiseId, userId);
        if (!validateState(participant, "departure")) {
            throw new ParticipantException(ParticipantErrorCode.INVALID_STATE);
        }
        participantEditor.departurePromise(participant);

        int departureCount = participantRetriever.countFirstDepartureByPromiseId(promiseId);
        if (departureCount == 1) {
            List<String> fcmTokens = participantRetriever.findFcmTokenByPromiseId(promiseId, userId);
            fcmService.sendBulk(fcmTokens, FcmMessageDto.of(FcmContent.FIRST_DEPARTURE, promiseId));
        }
    }

    @Transactional
    public void arrivalPromise(
            final Long userId,
            final Long promiseId
    ) {
        Participant participant = participantRetriever.findByPromiseIdAndUserId(promiseId, userId);
        if (!validateState(participant, "arrival")) {
            throw new ParticipantException(ParticipantErrorCode.INVALID_STATE);
        }
        participantEditor.arrivalPromise(participant);

        int arrivalCount = participantRetriever.countFirstArrivalByPromiseId(promiseId);
        if (arrivalCount == 1) {
            List<String> fcmTokens = participantRetriever.findFcmTokenByPromiseId(promiseId, userId);
            fcmService.sendBulk(fcmTokens, FcmMessageDto.of(FcmContent.FIRST_ARRIVAL, promiseId));
        }
    }

    @Transactional(readOnly = true)
    public PreparationStatusDto getPreparation(
            final Long userId,
            final Long promiseId
    ) {
        Participant participant = participantRetriever.findByPromiseIdAndUserId(promiseId, userId);
        return PreparationStatusDto.from(participant);
    }

    @Transactional(readOnly = true)
    public ParticipantsDto getParticipants(final Long promiseId) {
        List<ParticipantStatusUserInfoDto> participants = participantRetriever.findAllByPromiseIdWithUserInfo(promiseId);
        List<ParticipantDto> sortedParticipants = participants.stream()
                .map(this::createParticipantDto)
                .sorted(Comparator.comparing(ParticipantDto::state, Comparator.comparingInt(this::stateOrder)))
                .collect(Collectors.toList());

        return ParticipantsDto.from(sortedParticipants);
    }

    @Transactional(readOnly = true)
    public AvailableParticipantsDto getAvailableParticipants(
            final Long userId,
            final Long promiseId
    ) {
        //모임 내 멤버 목록
        List<MemberDto> members = memberRetreiver.findAllByPromiseId(promiseId);
        //나 제외
        Member authenticatedMember = memberRetreiver.findByUserIdAndPromiseId(userId, promiseId);
        members.removeIf(member -> member.memberId().equals(authenticatedMember.getId()));

        //약속에 참여 중인 멤버 id들 가져오기
        List<Long> participantIds = participantRetriever.findAllByPromiseId(promiseId).stream()
                                                                .map(participant -> participant.getMember().getId())
                                                                .toList();

        return AvailableParticipantsDto.of(members, participantIds);
    }

    @Transactional
    public void insertPreparationInfo(
            final Long userId,
            final Long promiseId,
            final PreparationInfoDto preparationInfoDto
    ) {
        Participant participant = participantRetriever.findByPromiseIdAndUserId(promiseId, userId);
        participantEditor.updatePreparationTime(participant, preparationInfoDto);
        participantEditor.updateTravelTime(participant, preparationInfoDto);
    }

    @Transactional(readOnly = true)
    public LateComersDto getLateComers(final Long promiseId) {
        Promise promise = promiseRetriever.findById(promiseId);
        
        if (promise.getTime().isAfter(LocalDateTime.now())) {
            return LateComersDto.of(promise, Collections.emptyList());
        }

        List<LateComerDto> lateComers = participantRetriever.findAllLateComersByPromiseId(promiseId);
        return LateComersDto.of(
                promise,
                lateComers.stream()
                        .map(lateComer -> LateComerDto.of(
                                lateComer.participantId(),
                                lateComer.name(),
                                lateComer.profileImg())
                        )
                        .collect(Collectors.toList())
        );
    }

    @Transactional
    public void leavePromise(
            final Long userId,
            final Long promiseId
    ) {
        Participant participant = participantRetriever.findByPromiseIdAndUserId(promiseId, userId);
        participantRemover.deleteById(participant.getId());
    }

    private boolean validateState(
            final Participant participant,
            final String status
    ) {
        switch (status) {
            case "preparation":
                return isNull(participant.getPreparationStartAt())
                        && isNull(participant.getDepartureAt())
                        && isNull(participant.getArrivalAt());
            case "departure":
                return isNotNull(participant.getPreparationStartAt())
                        && isNull(participant.getDepartureAt())
                        && isNull(participant.getArrivalAt());
            case "arrival":
                return isNotNull(participant.getPreparationStartAt())
                        && isNotNull(participant.getDepartureAt())
                        && isNull(participant.getArrivalAt());
            default:
                throw new IllegalArgumentException("Unknown status");
        }
    }

    private boolean isNull(final LocalDateTime time) {
        return time == null;
    }

    private boolean isNotNull(final LocalDateTime time) {
        return time != null;
    }

    private ParticipantDto createParticipantDto(final ParticipantStatusUserInfoDto dto) {
        String state = determineState(dto.preparationAt(), dto.departureAt(), dto.arrivalAt());  // 상태 결정 로직 호출

        return ParticipantDto.of(dto.participantId(), dto.memberId(), dto.name(), dto.profileImg(), state);
    }

    private String determineState(
            final LocalDateTime preparationAt,
            final LocalDateTime departureAt,
            final LocalDateTime arrivalAt
    ) {
        if (arrivalAt != null) {
            return "도착";
        } else if (departureAt != null) {
            return "이동중";
        } else if (preparationAt != null) {
            return "준비중";
        }
        return "꾸물중";
    }

    private int stateOrder(String state) {
        switch(state) {
            case "도착": return 1;
            case "이동중": return 2;
            case "준비중": return 3;
            case "꾸물중": return 4;
            default: return 5;
        }
    }
}
