package org.kkumulkkum.server.domain.participant.manager;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.domain.participant.Participant;
import org.kkumulkkum.server.api.participant.dto.request.PreparationInfoDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ParticipantEditor {

    public void preparePromise(final Participant participant) {
        participant.preparePromise();
    }

    public void departurePromise(final Participant participant) {
        participant.departurePromise();
    }

    public void arrivalPromise(final Participant participant) {
        participant.arrivalPromise();
    }

    public void updatePreparationTime(
            final Participant participant,
            final PreparationInfoDto preparationInfoDto
    ) {
        participant.updatePreparationTime(preparationInfoDto.preparationTime());
    }

    public void updateTravelTime(
            final Participant participant,
            final PreparationInfoDto preparationInfoDto
    ) {
        participant.updateTravelTime(preparationInfoDto.travelTime());
    }
}
