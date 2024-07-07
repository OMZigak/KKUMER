package org.kkumulkkum.server.repository;

import org.kkumulkkum.server.domain.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}
