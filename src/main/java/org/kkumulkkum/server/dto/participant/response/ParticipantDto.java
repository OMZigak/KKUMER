package org.kkumulkkum.server.dto.participant.response;

import org.kkumulkkum.server.domain.Participant;

public record ParticipantDto(
        Long id,
        String name,
        String profileImg,
        String state
) {
    public static ParticipantDto of(Long id, String name, String profileImg, String state) {
        return new ParticipantDto(
                id,
                name,
                profileImg,
                state
        );
    }
}
