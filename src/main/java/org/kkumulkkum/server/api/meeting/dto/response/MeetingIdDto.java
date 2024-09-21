package org.kkumulkkum.server.api.meeting.dto.response;

import org.kkumulkkum.server.domain.meeting.Meeting;

public record MeetingIdDto(
        Long meetingId
) {
    public static MeetingIdDto from(Meeting meeting) {
        return new MeetingIdDto(meeting.getId());
    }
}
