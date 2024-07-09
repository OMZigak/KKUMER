package org.kkumulkkum.server.service.participant;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.domain.Participant;
import org.kkumulkkum.server.exception.ParticipantException;
import org.kkumulkkum.server.exception.code.ParticipantErrorCode;
import org.kkumulkkum.server.repository.ParticipantRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ParticipantRetriever {

    private final ParticipantRepository participantRepository;

    public Participant findByPromiseIdAndUserId(Long promiseId, Long userId) {
        return participantRepository.findByPromiseIdAndUserId(promiseId, userId)
                .orElseThrow(() -> new ParticipantException(ParticipantErrorCode.NOT_JOINED_PROMISE));
    }
}
