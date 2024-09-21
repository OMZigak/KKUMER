package org.kkumulkkum.server.api.meeting.dto.response;

public record CreatedMeetingDto(
        Long meetingId,
        String invitationCode
) {
}
