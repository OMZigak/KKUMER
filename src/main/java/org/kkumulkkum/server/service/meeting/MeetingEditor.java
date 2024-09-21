package org.kkumulkkum.server.service.meeting;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.domain.meeting.Meeting;
import org.kkumulkkum.server.dto.meeting.request.MeetingCreateDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MeetingEditor {

    public void updateMeetingName(
            final Meeting meeting,
            final MeetingCreateDto meetingCreateDto
    ) {
        meeting.updateMeetingName(meetingCreateDto.name());
    }
}
