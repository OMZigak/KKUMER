package org.kkumulkkum.server.api.meeting.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.kkumulkkum.server.domain.meeting.Meeting;

import java.time.LocalDateTime;

public record MeetingDto (
        Long meetingId,
        String name,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime createdAt,
        Long metCount,
        String invitationCode
) {
    public static MeetingDto of(Meeting meeting, Long metCount) {
        return new MeetingDto(
                meeting.getId(),
                meeting.getName(),
                meeting.getCreatedAt(),
                metCount,
                meeting.getInvitationCode()
        );
    }
}
