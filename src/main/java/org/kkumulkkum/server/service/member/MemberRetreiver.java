package org.kkumulkkum.server.service.member;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.domain.Member;
import org.kkumulkkum.server.dto.member.MemberUserInfoDto;
import org.kkumulkkum.server.dto.member.response.MemberDto;
import org.kkumulkkum.server.repository.MemberRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MemberRetreiver {

    private final MemberRepository memberRepository;

    public boolean existsByMeetingIdAndUserId(Long meetingId, Long userId) {
        return memberRepository.existsByMeetingIdAndUserId(meetingId, userId);
    }

    public List<MemberUserInfoDto> findAllByMeetingId(Long meetingId) {
        return memberRepository.findAllByMeetingId(meetingId);
    }

    public boolean existsByPromiseIdAndUserId(Long promiseId, Long userId) {
        return memberRepository.existsByPromiseIdAndUserId(promiseId, userId);
    }
}
