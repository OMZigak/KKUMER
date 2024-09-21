package org.kkumulkkum.server.domain.meeting.repository;

import org.kkumulkkum.server.domain.meeting.Meeting;
import org.kkumulkkum.server.dto.meeting.MeetingMetCountDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {

    boolean existsByInvitationCode(String invitationCode);

    Optional<Meeting> findByInvitationCode(String invitationCode);

    @Query("SELECT m FROM Meeting m JOIN m.members mem WHERE mem.user.id = :userId")
    List<Meeting> findAllByUserId(Long userId);

    @Query("""
        SELECT new org.kkumulkkum.server.dto.meeting.MeetingMetCountDto (m, COUNT(p.id)) 
        FROM Meeting m 
        LEFT JOIN Promise p ON p.meeting.id = m.id AND p.isCompleted = true 
        WHERE m.id = :meetingId 
        GROUP BY m.id """)
    Optional<MeetingMetCountDto> findByIdWithMetCount(Long meetingId);

}
