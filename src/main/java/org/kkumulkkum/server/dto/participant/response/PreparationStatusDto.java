package org.kkumulkkum.server.dto.participant.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.kkumulkkum.server.domain.Participant;

import java.time.LocalDateTime;

public record PreparationStatusDto(
        Integer preparationTime,
        Integer travelTime,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "a h:mm", locale = "en")
        LocalDateTime preparationStartAt,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "a h:mm", locale = "en")
        LocalDateTime departureAt,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "a h:mm", locale = "en")
        LocalDateTime arrivalAt
) {
    public static PreparationStatusDto from(Participant participant) {
        return new PreparationStatusDto(
                participant.getPreparationTime(),
                participant.getTravelTime(),
                participant.getPreparationStartAt(),
                participant.getDepartureAt(),
                participant.getArrivalAt()
        );
    }
}
