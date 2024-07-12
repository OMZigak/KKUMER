package org.kkumulkkum.server.dto.participant;

import java.time.LocalDateTime;

public record ParticipantStatusUserInfoDto(
        Long participantId,
        Long memberId,
        String name,
        String profileImg,
        LocalDateTime preparationAt,
        LocalDateTime departureAt,
        LocalDateTime arrivalAt
) {
    public static ParticipantStatusUserInfoDto from(
            Long participantId,
            Long memberId,
            String name,
            String profileImg,
            LocalDateTime preparationAt,
            LocalDateTime departureAt,
            LocalDateTime arrivalAt
    ) {
        return new ParticipantStatusUserInfoDto(
                participantId,
                memberId,
                name,
                profileImg,
                preparationAt,
                departureAt,
                arrivalAt
        );
    }
}
