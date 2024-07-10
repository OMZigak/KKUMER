package org.kkumulkkum.server.service.participant;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kkumulkkum.server.domain.Participant;
import org.kkumulkkum.server.dto.member.MemberUserInfoDto;
import org.kkumulkkum.server.dto.member.response.MemberDto;
import org.kkumulkkum.server.dto.participant.ParticipantUserInfoDto;
import org.kkumulkkum.server.dto.participant.response.ParticipantDto;
import org.kkumulkkum.server.dto.participant.response.ParticipantsDto;
import org.kkumulkkum.server.dto.participant.response.PreparationStatusDto;
import org.kkumulkkum.server.exception.MeetingException;
import org.kkumulkkum.server.exception.ParticipantException;
import org.kkumulkkum.server.exception.code.MeetingErrorCode;
import org.kkumulkkum.server.exception.code.ParticipantErrorCode;
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
    public ParticipantsDto getParticipants(final Long userId, final Long promiseId) {
//        validateParticipant(userId, promiseId); participant 아니여도 모임에만 속해있으면 다른 약속들 볼 수 있잖아
        // TODO: Member 검증
        List<ParticipantUserInfoDto> participants = participantRetriever.findAllByPromiseId(promiseId);
        return ParticipantsDto.of(
                participants.stream()
                        .map(participant -> createParticipantDto(participant, userId, promiseId))
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

//    private void validateParticipant(Long userId, Long promiseId) {
//        if (!participantRetriever.existsByPromiseIdAndUserId(promiseId, userId)) {
//            throw new ParticipantException(ParticipantErrorCode.FORBIDDEN_PARTICIPANT);
//        }
//    }

    private ParticipantDto createParticipantDto(ParticipantUserInfoDto dto, Long userId, Long promiseId) {
        String profileImage = (dto.profileImg() != null) ? dto.profileImg() : DEFAULT_PROFILE_IMG;
        Participant participant = participantRetriever.findByPromiseIdAndUserId(promiseId, userId);
        String state = determineState(participant);  // 상태 결정 로직 호출

        return ParticipantDto.of(dto.id(), dto.name(), profileImage, state);
    }

    private String determineState(Participant participant) {
        if (participant.getArrivalAt() != null) {
            return "도착함";
        } else if (participant.getDepartureAt() != null) {
            return "이동중";
        } else if (participant.getPreparationStartAt() != null) {
            return "준비중";
        }
        return "꾸물중";
    }
}
