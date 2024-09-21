package org.kkumulkkum.server.service.meeting;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.domain.meeting.Meeting;
import org.kkumulkkum.server.domain.meeting.repository.MeetingRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MeetingSaver {

    private final MeetingRepository meetingRepository;

    public void save(final Meeting meeting) {
        meetingRepository.save(meeting);
    }
}
