package org.kkumulkkum.server.service.participant;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.domain.Participant;
import org.kkumulkkum.server.dto.participant.ParticipantStatusUserInfoDto;
import org.kkumulkkum.server.dto.participant.response.LateComerDto;
import org.kkumulkkum.server.exception.ParticipantException;
import org.kkumulkkum.server.exception.code.ParticipantErrorCode;
import org.kkumulkkum.server.repository.ParticipantRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ParticipantRetriever {

    private final ParticipantRepository participantRepository;

    public Participant findByPromiseIdAndUserId(
            final Long promiseId,
            final Long userId
    ) {
        return participantRepository.findByPromiseIdAndUserId(promiseId, userId)
                .orElseThrow(() -> new ParticipantException(ParticipantErrorCode.NOT_JOINED_PROMISE));
    }

    public List<Participant> findAllByPromiseId(final Long promiseId) {
        return participantRepository.findAllByPromiseId(promiseId);
    }

    public List<ParticipantStatusUserInfoDto> findAllByPromiseIdWithUserInfo(final Long promiseId) {
        return participantRepository.findAllByPromiseIdWithUserInfo(promiseId);
    }

    public List<LateComerDto> findAllLateComersByPromiseId(final Long promiseId) {
        return participantRepository.findAllLateComersByPromiseId(promiseId);
    }

    public boolean existsByPromiseIdAndUserId(
            final Long promiseId,
            final Long userId
    ) {
        return participantRepository.existsByPromiseIdAndUserId(promiseId, userId);
    }

    public int countFirstPreparationByPromiseId(final Long promiseId) {
        return participantRepository.countFirstPreparationByPromiseId(promiseId);
    }

    public int countFirstDepartureByPromiseId(final Long promiseId) {
        return participantRepository.countFirstDepartureByPromiseId(promiseId);
    }

    public int countFirstArrivalByPromiseId(final Long promiseId) {
        return participantRepository.countFirstArrivalByPromiseId(promiseId);
    }

    public List<String> findFcmTokenByPromiseId(final Long promiseId, final Long userId) {
        return participantRepository.findFcmTokenByPromiseId(promiseId, userId);
    }

}
