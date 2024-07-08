package org.kkumulkkum.server.repository;

import org.kkumulkkum.server.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Member m WHERE m.user.id = :userId AND m.meeting.id = :meetingId")
    public boolean existsByMeetingIdAndUserId(Long meetingId, Long userId);
}
