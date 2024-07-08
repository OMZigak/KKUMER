package org.kkumulkkum.server.service.member;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.domain.Member;
import org.kkumulkkum.server.repository.MemberRepository;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class MemberSaver {

    private final MemberRepository memberRepository;
    
    public void save(final Member member) {
        memberRepository.save(member);
    }
}
