package org.kkumulkkum.server.service.participant;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.domain.Participant;
import org.kkumulkkum.server.dto.participant.ParticipantUserInfoDto;
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

    public boolean existsByPromiseIdAndUserId(Long promiseId, Long userId) {
        return participantRepository.existsByPromiseIdAndUserId(promiseId, userId);
    }

    public List<ParticipantUserInfoDto> findAllByPromiseId(Long promiseId) {
        return participantRepository.findAllByPromiseId(promiseId);
    }
}
