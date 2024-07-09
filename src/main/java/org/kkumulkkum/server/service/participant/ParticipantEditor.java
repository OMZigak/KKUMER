package org.kkumulkkum.server.service.participant;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.domain.Participant;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ParticipantEditor {

    public void preparePromise(final Participant participant) {
        participant.preparePromise();
    }
}
