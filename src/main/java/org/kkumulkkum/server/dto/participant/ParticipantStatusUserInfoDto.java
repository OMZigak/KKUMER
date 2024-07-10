package org.kkumulkkum.server.dto.participant;

import java.time.LocalDateTime;

public record ParticipantStatusUserInfoDto(
        Long id,
        String name,
        String profileImg,
        LocalDateTime preparationAt,
        LocalDateTime departureAt,
        LocalDateTime arrivalAt
) {
    public static ParticipantStatusUserInfoDto from(
            Long id,
            String name,
            String profileImg,
            LocalDateTime preparationAt,
            LocalDateTime departureAt,
            LocalDateTime arrivalAt
    ) {
        return new ParticipantStatusUserInfoDto(
                id,
                name,
                profileImg,
                preparationAt,
                departureAt,
                arrivalAt
        );
    }
}
