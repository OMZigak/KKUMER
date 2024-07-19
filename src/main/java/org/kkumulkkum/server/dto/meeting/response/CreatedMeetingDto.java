package org.kkumulkkum.server.dto.meeting.response;

public record CreatedMeetingDto(
        Long meetingId,
        String invitationCode
) {
}
