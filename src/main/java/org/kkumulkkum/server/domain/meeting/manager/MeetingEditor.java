package org.kkumulkkum.server.domain.meeting.manager;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.domain.meeting.Meeting;
import org.kkumulkkum.server.api.meeting.dto.request.MeetingCreateDto;
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
