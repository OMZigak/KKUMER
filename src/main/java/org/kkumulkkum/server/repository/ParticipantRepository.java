package org.kkumulkkum.server.repository;

import org.kkumulkkum.server.domain.Participant;
import org.kkumulkkum.server.dto.participant.ParticipantStatusUserInfoDto;
import org.kkumulkkum.server.dto.participant.response.LateComerDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    @Query("SELECT p FROM Participant p " +
            "JOIN Member m ON p.member.id = m.id " +
            "WHERE p.promise.id = :promiseId AND m.user.id = :userId")
    Optional<Participant> findByPromiseIdAndUserId(Long promiseId, Long userId);

    @Query("SELECT new org.kkumulkkum.server.dto.participant.ParticipantStatusUserInfoDto " +
            "(p.id, ui.name, ui.profileImg, p.preparationStartAt, p.departureAt, p.arrivalAt) " +
            "FROM Participant p " +
            "JOIN Member m ON p.member.id = m.id " +
            "JOIN UserInfo ui ON m.user.id = ui.user.id " +
            "WHERE p.promise.id = :promiseId")
    List<ParticipantStatusUserInfoDto> findAllByPromiseId(Long promiseId);

    @Query("SELECT new org.kkumulkkum.server.dto.participant.response.LateComerDto " +
            "(p.id, ui.name, ui.profileImg) " +
            "FROM Participant p " +
            "JOIN Member m ON p.member.id = m.id " +
            "JOIN UserInfo ui ON m.user.id = ui.user.id " +
            "JOIN Promise pr ON p.promise.id = pr.id " +
            "WHERE p.promise.id = :promiseId AND p.arrivalAt > pr.time")
    List<LateComerDto> findAllLateComersByPromiseId(Long promiseId);

}
