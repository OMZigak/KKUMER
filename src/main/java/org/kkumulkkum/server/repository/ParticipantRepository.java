package org.kkumulkkum.server.repository;

import org.kkumulkkum.server.domain.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    @Query("SELECT p FROM Participant p " +
            "JOIN Member m ON p.member.id = m.id " +
            "WHERE p.promise.id = :promiseId AND m.user.id = :userId")
    Optional<Participant> findByPromiseIdAndUserId(Long promiseId, Long userId);
}
