package org.kkumulkkum.server.repository;

import org.kkumulkkum.server.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
