package org.kkumulkkum.server.dto.participant.response;

public record ParticipantDto(
        Long participantId,
        Long memberId,
        String name,
        String profileImg,
        String state
) {
    public static ParticipantDto of(Long participantId, Long memberId, String name, String profileImg, String state) {
        return new ParticipantDto(
                participantId,
                memberId,
                name,
                profileImg,
                state
        );
    }
}
