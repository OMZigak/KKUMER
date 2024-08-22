package org.kkumulkkum.server.service.member;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.domain.Member;
import org.kkumulkkum.server.repository.MemberRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MemberRemover {

    private final MemberRepository memberRepository;

    public void deleteAll(final List<Member> members) {
        memberRepository.deleteAll(members);
    }

    public void deleteById(final Long memberId) {
        memberRepository.deleteById(memberId);
    }
}
