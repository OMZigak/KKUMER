package org.kkumulkkum.server.api.meeting.dto.response;

import org.kkumulkkum.server.domain.meeting.Meeting;

import java.util.List;

public record MeetingsDto (
        int count,
        List<MeetingDto> meetings
) {

    public static MeetingsDto from(List<Meeting> meetings) {
        return new MeetingsDto(
                meetings.size(),
                meetings.stream()
                        .map(MeetingDto::from)
                        .toList()
        );
    }

    public record MeetingDto (
            Long meetingId,
            String name,
            int memberCount
    ) {
        public static MeetingDto from(Meeting meeting) {
            return new MeetingDto(
                    meeting.getId(),
                    meeting.getName(),
                    meeting.getMembers().size()
            );
        }
    }
}
