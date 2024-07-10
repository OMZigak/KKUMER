package org.kkumulkkum.server.dto.participant.request;

import jakarta.validation.constraints.Size;

public record PreparationInfoDto(
        @Size(max = 1440)
        int preparationTime,
        @Size(max = 1440)
        int travelTime
) {
}
