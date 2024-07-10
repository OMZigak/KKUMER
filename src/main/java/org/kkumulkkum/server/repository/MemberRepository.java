package org.kkumulkkum.server.repository;

import org.kkumulkkum.server.domain.Member;
import org.kkumulkkum.server.dto.member.response.MemberDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Member m WHERE m.user.id = :userId AND m.meeting.id = :meetingId")
    boolean existsByMeetingIdAndUserId(Long meetingId, Long userId);

    @Query("SELECT new org.kkumulkkum.server.dto.member.response.MemberDto" +
            "(m.id, ui.name, ui.profileImg) " +
            "FROM Member m " +
            "JOIN UserInfo ui ON m.user.id = ui.user.id " +
            "WHERE m.meeting.id = :meetingId")
    List<MemberDto> findAllByMeetingId(Long meetingId);
}
