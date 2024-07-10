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

    public Participant findByPromiseIdAndUserId(Long promiseId, Long userId) {
        return participantRepository.findByPromiseIdAndUserId(promiseId, userId)
                .orElseThrow(() -> new ParticipantException(ParticipantErrorCode.NOT_JOINED_PROMISE));
    }

    public List<ParticipantStatusUserInfoDto> findAllByPromiseId(Long promiseId) {
        return participantRepository.findAllByPromiseId(promiseId);
    }

    public List<LateComerDto> findAllLateComersByPromiseId(Long promiseId) {
        return participantRepository.findAllLateComersByPromiseId(promiseId);
    }

}
