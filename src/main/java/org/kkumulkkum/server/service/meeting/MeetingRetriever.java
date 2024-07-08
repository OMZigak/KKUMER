package org.kkumulkkum.server.service.meeting;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.domain.Meeting;
import org.kkumulkkum.server.exception.MeetingException;
import org.kkumulkkum.server.exception.code.MeetingErrorCode;
import org.kkumulkkum.server.repository.MeetingRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MeetingRetriever {

    private final MeetingRepository meetingRepository;

    public boolean existsByInvitationCode(String invitationCode) {
        return meetingRepository.existsByInvitationCode(invitationCode);
    }

    public Meeting findByInvitationCode(String invitationCode) {
        return meetingRepository.findByInvitationCode(invitationCode)
                .orElseThrow(() -> new MeetingException(MeetingErrorCode.NOT_FOUND_MEETING));
    }

    public List<Meeting> findAllByUserId(Long userId) {
        return meetingRepository.findAllByUserId(userId);
    }
}
