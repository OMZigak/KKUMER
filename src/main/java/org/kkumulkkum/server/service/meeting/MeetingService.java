package org.kkumulkkum.server.service.meeting;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.domain.Meeting;
import org.kkumulkkum.server.domain.Member;
import org.kkumulkkum.server.dto.meeting.request.MeetingCreateDto;
import org.kkumulkkum.server.dto.meeting.request.MeetingRegisterDto;
import org.kkumulkkum.server.dto.meeting.response.CreatedMeetingDto;
import org.kkumulkkum.server.dto.meeting.response.MeetingsDto;
import org.kkumulkkum.server.exception.MeetingException;
import org.kkumulkkum.server.exception.code.MeetingErrorCode;
import org.kkumulkkum.server.service.member.MemberRetreiver;
import org.kkumulkkum.server.service.member.MemberSaver;
import org.kkumulkkum.server.service.user.UserRetriever;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MeetingService {

    private final MeetingSaver meetingSaver;
    private final MeetingRetriever meetingRetriever;
    private final UserRetriever userRetriever;
    private final MemberSaver memberSaver;
    private final MemberRetreiver memberRetreiver;

    public CreatedMeetingDto createMeeting(
            Long userId, MeetingCreateDto meetingCreateDto
    ) {
        String invitationCode = generateInvitationCode();

        Meeting meeting = Meeting.builder()
                .name(meetingCreateDto.name())
                .invitationCode(invitationCode)
                .build();
        meetingSaver.save(meeting);

        memberSaver.save(Member.builder()
                .meeting(meeting)
                .user(userRetriever.findById(userId))
                .build());

        return new CreatedMeetingDto(meeting.getId(), meeting.getInvitationCode());
    }

    public void registerMeeting(Long userId, MeetingRegisterDto meetingRegisterDto) {
        Meeting meeting = meetingRetriever.findByInvitationCode(meetingRegisterDto.invitationCode());
        Member member = Member.builder()
                .meeting(meeting)
                .user(userRetriever.findById(userId))
                .build();
        if (memberRetreiver.existsByMeetingIdAndUserId(meeting.getId(), userId)) {
            throw new MeetingException(MeetingErrorCode.ALREADY_JOINED);
        }
        memberSaver.save(member);
    }

    public MeetingsDto getMeetings(Long userId) {
        List<Meeting> meetings = meetingRetriever.findAllByUserId(userId);
        return MeetingsDto.of(meetings);
    }

    private String generateInvitationCode() {
        String invitationCode;

        do {
            invitationCode = generateRandomCode();
        } while (meetingRetriever.existsByInvitationCode(invitationCode));

        return invitationCode;
    }

    private String generateRandomCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder codeBuilder = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(characters.length());
            codeBuilder.append(characters.charAt(index));
        }

        return codeBuilder.toString();
    }

}
