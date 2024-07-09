package org.kkumulkkum.server.service.participant;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kkumulkkum.server.domain.Participant;
import org.kkumulkkum.server.dto.participant.response.PreparationStatusDto;
import org.kkumulkkum.server.exception.ParticipantException;
import org.kkumulkkum.server.exception.code.ParticipantErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParticipantService {

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
}
