package org.kkumulkkum.server.repository;

import org.kkumulkkum.server.domain.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {

    boolean existsByInvitationCode(String invitationCode);

    Optional<Meeting> findByInvitationCode(String invitationCode);

    @Query("SELECT m FROM Meeting m JOIN m.members mem WHERE mem.user.id = :userId")
    List<Meeting> findAllByUserId(Long userId);

}
