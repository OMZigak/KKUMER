package org.kkumulkkum.server.dto.meeting.response;

import org.kkumulkkum.server.domain.meeting.Meeting;

public record MeetingIdDto(
        Long meetingId
) {
    public static MeetingIdDto from(Meeting meeting) {
        return new MeetingIdDto(meeting.getId());
    }
}
