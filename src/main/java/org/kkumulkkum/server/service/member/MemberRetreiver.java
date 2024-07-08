package org.kkumulkkum.server.service.member;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.repository.MemberRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberRetreiver {

    private final MemberRepository memberRepository;

    public boolean existsByMeetingIdAndUserId(Long meetingId, Long userId) {
        return memberRepository.existsByMeetingIdAndUserId(meetingId, userId);
    }
}
