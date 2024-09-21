package org.kkumulkkum.server.api.participant.dto.request;

import jakarta.validation.constraints.Size;

public record PreparationInfoDto(
        @Size(max = 1440)
        Integer preparationTime,
        @Size(max = 1440)
        Integer travelTime
) {
}
