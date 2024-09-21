package org.kkumulkkum.server.api.participant.dto.response;

import java.util.List;

public record ParticipantsDto(
        int participantCount,
        List<ParticipantDto> participants
) {
    public static ParticipantsDto from(List<ParticipantDto> participants) {
        return new ParticipantsDto(
                participants.size(),
                participants
        );
    }
}
