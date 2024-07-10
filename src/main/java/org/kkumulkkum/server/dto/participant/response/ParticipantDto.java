package org.kkumulkkum.server.dto.participant.response;

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
