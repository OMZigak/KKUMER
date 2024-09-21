package org.kkumulkkum.server.dto.meeting;

import org.kkumulkkum.server.domain.meeting.Meeting;

public record MeetingMetCountDto (
        Meeting meeting,
        Long metCount
) {
}
