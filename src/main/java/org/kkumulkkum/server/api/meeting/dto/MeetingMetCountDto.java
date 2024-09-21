package org.kkumulkkum.server.api.meeting.dto;

import org.kkumulkkum.server.domain.meeting.Meeting;

public record MeetingMetCountDto (
        Meeting meeting,
        Long metCount
) {
}
