package org.kkumulkkum.server.dto.meeting;

import org.kkumulkkum.server.domain.Meeting;

public record MeetingMetCountDto (
        Meeting meeting,
        Long metCount
) {
}
