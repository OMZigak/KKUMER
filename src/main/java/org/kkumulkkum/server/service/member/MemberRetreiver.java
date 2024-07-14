package org.kkumulkkum.server.service.member;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.dto.member.response.MemberDto;
import org.kkumulkkum.server.repository.MemberRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MemberRetreiver {

    private final MemberRepository memberRepository;

    public boolean existsByMeetingIdAndUserId(
            final Long meetingId,
            final Long userId
    ) {
        return memberRepository.existsByMeetingIdAndUserId(meetingId, userId);
    }

    public List<MemberDto> findAllByMeetingId(final Long meetingId) {
        return memberRepository.findAllByMeetingId(meetingId);
    }

    public boolean existsByPromiseIdAndUserId(
            final Long promiseId,
            final Long userId
    ) {
        return memberRepository.existsByPromiseIdAndUserId(promiseId, userId);
    }
}
