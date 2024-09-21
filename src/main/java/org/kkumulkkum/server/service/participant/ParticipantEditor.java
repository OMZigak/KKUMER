package org.kkumulkkum.server.service.participant;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.domain.participant.Participant;
import org.kkumulkkum.server.dto.participant.request.PreparationInfoDto;
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
