package org.kkumulkkum.server.service.meeting;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.repository.MeetingRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MeetingRetriever {

    private final MeetingRepository meetingRepository;

    public boolean existsByInvitationCode(String invitationCode) {
        return meetingRepository.existsByInvitationCode(invitationCode);
    }

}
