package org.kkumulkkum.server.repository;

import org.kkumulkkum.server.domain.Participant;
import org.kkumulkkum.server.dto.participant.ParticipantUserInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    @Query("SELECT p FROM Participant p " +
            "JOIN Member m ON p.member.id = m.id " +
            "WHERE p.promise.id = :promiseId AND m.user.id = :userId")
    Optional<Participant> findByPromiseIdAndUserId(Long promiseId, Long userId);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Participant p JOIN Member m ON p.member.id = m.id " +
            "WHERE p.promise.id = :promiseId AND m.user.id = :userId")
    boolean existsByPromiseIdAndUserId(Long promiseId, Long userId);

    @Query("SELECT new org.kkumulkkum.server.dto.participant.ParticipantUserInfoDto" +
            "(p.id, ui.name, ui.profileImg) " +
            "FROM Participant p " +
            "JOIN Member m ON p.member.id = m.id " +
            "JOIN UserInfo ui ON m.user.id = ui.user.id " +
            "WHERE p.promise.id = :promiseId")
    List<ParticipantUserInfoDto> findAllByPromiseId(Long promiseId);

    @Query("SELECT new org.kkumulkkum.server.dto.participant.ParticipantUserInfoDto" +
            "(p.id, ui.name, ui.profileImg) " +
            "FROM Participant p " +
            "JOIN Member m ON p.member.id = m.id " +
            "JOIN UserInfo ui ON m.user.id = ui.user.id " +
            "JOIN Promise pr ON p.promise.id = pr.id " +
            "WHERE p.promise.id = :promiseId AND p.arrivalAt > pr.time")
    List<ParticipantUserInfoDto> findAllLateComersByPromiseId(Long promiseId);

}
