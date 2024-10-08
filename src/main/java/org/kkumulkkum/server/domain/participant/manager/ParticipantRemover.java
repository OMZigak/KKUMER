package org.kkumulkkum.server.domain.participant.manager;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.domain.participant.Participant;
import org.kkumulkkum.server.domain.participant.repository.ParticipantRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ParticipantRemover {

    private final ParticipantRepository participantRepository;

    public void deleteByMemberId(final Long memberId) {
        participantRepository.deleteByMemberId(memberId);
    }

    public void deleteById(final Long id) {
        participantRepository.deleteById(id);
    }

    public void deleteAll(List<Participant> participants) {
        participantRepository.deleteAllInBatch(participants);
    }
}
