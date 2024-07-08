package org.kkumulkkum.server.repository;

import org.kkumulkkum.server.domain.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {

    boolean existsByInvitationCode(String invitationCode);

    Optional<Meeting> findByInvitationCode(String invitationCode);

}
