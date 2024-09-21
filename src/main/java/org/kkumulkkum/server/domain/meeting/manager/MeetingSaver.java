package org.kkumulkkum.server.domain.meeting.manager;

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
