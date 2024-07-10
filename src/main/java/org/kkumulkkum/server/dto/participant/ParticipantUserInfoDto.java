package org.kkumulkkum.server.dto.participant;

public record ParticipantUserInfoDto(
        Long id,
        String name,
        String profileImg
) {
    public static ParticipantUserInfoDto from(
            Long id,
            String name,
            String profileImg
    ) {
        return new ParticipantUserInfoDto(
                id,
                name,
                profileImg
        );
    }
}
