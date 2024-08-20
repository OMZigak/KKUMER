package org.kkumulkkum.server.service.participant;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.domain.Participant;
import org.kkumulkkum.server.repository.ParticipantRepository;
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
