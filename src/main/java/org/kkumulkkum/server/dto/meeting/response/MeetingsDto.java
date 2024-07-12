package org.kkumulkkum.server.dto.meeting.response;

import org.kkumulkkum.server.domain.Meeting;

import java.util.List;

public record MeetingsDto (
        int count,
        List<MeetingDto> meetings
) {

    public static MeetingsDto of(List<Meeting> meetings) {
        return new MeetingsDto(
                meetings.size(),
                meetings.stream()
                        .map(MeetingDto::of)
                        .toList()
        );
    }

    public record MeetingDto (
            Long meetingId,
            String name,
            int memberCount
    ) {
        public static MeetingDto of(Meeting meeting) {
            return new MeetingDto(
                    meeting.getId(),
                    meeting.getName(),
                    meeting.getMembers().size()
            );
        }
    }
}
