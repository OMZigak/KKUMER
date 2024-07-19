package org.kkumulkkum.server.service.meeting;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.domain.Meeting;
import org.kkumulkkum.server.dto.meeting.MeetingMetCountDto;
import org.kkumulkkum.server.exception.MeetingException;
import org.kkumulkkum.server.exception.code.MeetingErrorCode;
import org.kkumulkkum.server.repository.MeetingRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MeetingRetriever {

    private final MeetingRepository meetingRepository;

    public boolean existsByInvitationCode(final String invitationCode) {
        return meetingRepository.existsByInvitationCode(invitationCode);
    }

    public Meeting findByInvitationCode(final String invitationCode) {
        return meetingRepository.findByInvitationCode(invitationCode)
                .orElseThrow(() -> new MeetingException(MeetingErrorCode.NOT_FOUND_MEETING));
    }

    public List<Meeting> findAllByUserId(final Long userId) {
        return meetingRepository.findAllByUserId(userId);
    }

    public Meeting findById(final Long meetingId) {
        return meetingRepository.findById(meetingId)
                .orElseThrow(() -> new MeetingException(MeetingErrorCode.NOT_FOUND_MEETING));
    }

    public MeetingMetCountDto findByIdWithMetCount(final Long meetingId) {
        return meetingRepository.findByIdWithMetCount(meetingId)
                .orElseThrow(() -> new MeetingException(MeetingErrorCode.NOT_FOUND_MEETING));
    }
}
