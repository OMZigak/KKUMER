package org.kkumulkkum.server.domain.member.manager;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.domain.member.Member;
import org.kkumulkkum.server.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class MemberSaver {

    private final MemberRepository memberRepository;
    
    public void save(final Member member) {
        memberRepository.save(member);
    }
}
