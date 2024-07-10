package org.kkumulkkum.server.dto.participant.response;

public record LateComerDto(
        Long id,
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
