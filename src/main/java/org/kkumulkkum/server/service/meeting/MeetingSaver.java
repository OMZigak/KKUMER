package org.kkumulkkum.server.service.meeting;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.domain.Meeting;
import org.kkumulkkum.server.repository.MeetingRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MeetingSaver {

    private final MeetingRepository meetingRepository;

    public void save(final Meeting meeting) {
        meetingRepository.save(meeting);
    }
}
