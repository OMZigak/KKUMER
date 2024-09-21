package org.kkumulkkum.server.service.meeting;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.domain.meeting.repository.MeetingRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MeetingRemover {

    private final MeetingRepository meetingRepository;

    public void deleteById(final Long meetingId) {
        meetingRepository.deleteById(meetingId);
    }
}
