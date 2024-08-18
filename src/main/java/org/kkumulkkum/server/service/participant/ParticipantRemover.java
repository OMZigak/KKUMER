package org.kkumulkkum.server.service.participant;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.repository.ParticipantRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ParticipantRemover {

    private final ParticipantRepository participantRepository;

    public void deleteByMemberId(final Long memberId) {
        participantRepository.deleteByMemberId(memberId);
    }
}
