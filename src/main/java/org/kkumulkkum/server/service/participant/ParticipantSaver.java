package org.kkumulkkum.server.service.participant;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.domain.Participant;
import org.kkumulkkum.server.repository.ParticipantRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ParticipantSaver {

    private final ParticipantRepository participantRepository;

    public void save(Participant participant) {
        participantRepository.save(participant);
    }

    public void saveAll(List<Participant> participants) {
        participantRepository.saveAll(participants);
    }
}
