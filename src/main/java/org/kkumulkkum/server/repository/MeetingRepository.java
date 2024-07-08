package org.kkumulkkum.server.repository;

import org.kkumulkkum.server.domain.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {

    boolean existsByInvitationCode(String invitationCode);

}
