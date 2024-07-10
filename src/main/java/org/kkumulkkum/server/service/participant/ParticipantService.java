package org.kkumulkkum.server.service.participant;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kkumulkkum.server.domain.Participant;
import org.kkumulkkum.server.domain.Promise;
import org.kkumulkkum.server.dto.participant.ParticipantStatusUserInfoDto;
import org.kkumulkkum.server.dto.participant.ParticipantUserInfoDto;
import org.kkumulkkum.server.dto.participant.request.PreparationInfoDto;
import org.kkumulkkum.server.dto.participant.response.*;
import org.kkumulkkum.server.exception.ParticipantException;
import org.kkumulkkum.server.exception.code.ParticipantErrorCode;
import org.kkumulkkum.server.service.promise.PromiseRetriever;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParticipantService {

    @Value("${s3.default.profile-img}")
    private String DEFAULT_PROFILE_IMG;

    private final ParticipantRetriever participantRetriever;
    private final ParticipantEditor participantEditor;
    private final PromiseRetriever promiseRetriever;

    @Transactional
    public void preparePromise(final Long userId, final Long promiseId) {
        Participant participant = participantRetriever.findByPromiseIdAndUserId(promiseId, userId);
        if (!validateState(participant, "preperation")) {
            throw new ParticipantException(ParticipantErrorCode.INVALID_STATE);
        }
        participantEditor.preparePromise(participant);
    }

    @Transactional
    public void departurePromise(final Long userId, final Long promiseId) {
        Participant participant = participantRetriever.findByPromiseIdAndUserId(promiseId, userId);
        if (!validateState(participant, "departure")) {
            throw new ParticipantException(ParticipantErrorCode.INVALID_STATE);
        }
        participantEditor.departurePromise(participant);
    }

    @Transactional
    public void arrivalPromise(final Long userId, final Long promiseId) {
        Participant participant = participantRetriever.findByPromiseIdAndUserId(promiseId, userId);
        if (!validateState(participant, "arrival")) {
            throw new ParticipantException(ParticipantErrorCode.INVALID_STATE);
        }
        participantEditor.arrivalPromise(participant);
    }

    @Transactional(readOnly = true)
    public PreparationStatusDto getPreparation(final Long userId, final Long promiseId) {
        Participant participant = participantRetriever.findByPromiseIdAndUserId(promiseId, userId);
        return PreparationStatusDto.from(participant);
    }

    @Transactional(readOnly = true)
    public ParticipantsDto getParticipants(final Long promiseId) {
        List<ParticipantStatusUserInfoDto> participants = participantRetriever.findAllByPromiseId(promiseId);
        return ParticipantsDto.from(
                participants.stream()
                        .map(this::createParticipantDto)
                        .collect(Collectors.toList())
        );
    }

    @Transactional
    public void insertPreparationInfo(final Long userId, final Long promiseId, final PreparationInfoDto preparationInfoDto) {
        Participant participant = participantRetriever.findByPromiseIdAndUserId(promiseId, userId);
        participantEditor.updatePreparationTime(participant, preparationInfoDto);
        participantEditor.updateTravelTime(participant, preparationInfoDto);
    }

    @Transactional(readOnly = true)
    public LateComersDto getLateComers(final Long promiseId) {
        Promise promise = promiseRetriever.findById(promiseId);
        List<ParticipantUserInfoDto> lateComers = participantRetriever.findAllLateComersByPromiseId(promiseId);
        return LateComersDto.of(
                promise,
                lateComers.stream()
                        .map(this::createLateComerDto)
                        .collect(Collectors.toList())
        );
    }

    private boolean validateState(final Participant participant, final String status) {
        switch (status) {
            case "preperation":
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

    private boolean isNull(LocalDateTime time) {
        return time == null;
    }

    private boolean isNotNull(LocalDateTime time) {
        return time != null;
    }

    private ParticipantDto createParticipantDto(ParticipantStatusUserInfoDto dto) {
        String profileImage = (dto.profileImg() != null) ? dto.profileImg() : DEFAULT_PROFILE_IMG;
        String state = determineState(dto.preparationAt(), dto.departureAt(), dto.arrivalAt());  // 상태 결정 로직 호출

        return ParticipantDto.of(dto.id(), dto.name(), profileImage, state);
    }

    private LateComerDto createLateComerDto(ParticipantUserInfoDto dto) {
        String profileImage = (dto.profileImg() != null) ? dto.profileImg() : DEFAULT_PROFILE_IMG;

        return LateComerDto.of(dto.id(), dto.name(), profileImage);
    }

    private String determineState(
            LocalDateTime preparationAt,
            LocalDateTime departureAt,
            LocalDateTime arrivalAt
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
}
