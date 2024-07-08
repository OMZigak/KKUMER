package org.kkumulkkum.server.dto.meeting.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.kkumulkkum.server.domain.Meeting;

import java.time.LocalDateTime;

public record MeetingDto (
        Long id,
        String name,
        @JsonFormat(pattern = "yyyy-MM-DD")
        LocalDateTime createdAt,
        int metCount,
        String invitationCode
) {
    public static MeetingDto of(Meeting meeting) {
        return new MeetingDto(
                meeting.getId(),
                meeting.getName(),
                meeting.getCreatedAt(),
                meeting.getMembers().size(),
                meeting.getInvitationCode()
        );
    }
}