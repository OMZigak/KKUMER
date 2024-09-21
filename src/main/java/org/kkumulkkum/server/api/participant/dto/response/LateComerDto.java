package org.kkumulkkum.server.api.participant.dto.response;

public record LateComerDto(
        Long participantId,
        String name,
        String profileImg
) {
    public static LateComerDto of(Long id, String name, String profileImg) {
        return new LateComerDto(
                id,
                name,
                profileImg
        );
    }
}
