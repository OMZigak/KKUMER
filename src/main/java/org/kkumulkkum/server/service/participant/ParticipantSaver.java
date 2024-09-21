package org.kkumulkkum.server.service.participant;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.domain.participant.Participant;
import org.kkumulkkum.server.domain.participant.repository.ParticipantRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ParticipantSaver {

    private final ParticipantRepository participantRepository;

    public void saveAll(final List<Participant> participants) {
        participantRepository.saveAll(participants);
    }
}
