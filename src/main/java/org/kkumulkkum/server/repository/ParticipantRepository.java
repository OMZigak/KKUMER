package org.kkumulkkum.server.repository;

import org.kkumulkkum.server.domain.Participant;
import org.kkumulkkum.server.dto.participant.ParticipantStatusUserInfoDto;
import org.kkumulkkum.server.dto.participant.response.LateComerDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    @Query("""
            SELECT p FROM Participant p
            JOIN FETCH Member m ON p.member.id = m.id
            WHERE p.promise.id = :promiseId AND m.user.id = :userId""")
    Optional<Participant> findByPromiseIdAndUserId(Long promiseId, Long userId);

    @Query("""
            SELECT new org.kkumulkkum.server.dto.participant.ParticipantStatusUserInfoDto
            (p.id, p.member.id, ui.name, ui.profileImg, p.preparationStartAt, p.departureAt, p.arrivalAt)
            FROM Participant p
            JOIN Member m ON p.member.id = m.id
            JOIN UserInfo ui ON m.user.id = ui.user.id
            WHERE p.promise.id = :promiseId""")
    List<ParticipantStatusUserInfoDto> findAllByPromiseIdWithUserInfo(Long promiseId);

    @Query("""
            SELECT new org.kkumulkkum.server.dto.participant.response.LateComerDto
            (p.id, ui.name, ui.profileImg)
            FROM Participant p 
            JOIN Member m ON p.member.id = m.id 
            JOIN UserInfo ui ON m.user.id = ui.user.id 
            JOIN Promise pr ON p.promise.id = pr.id 
            WHERE p.promise.id = :promiseId AND (p.arrivalAt IS NULL OR p.arrivalAt > pr.time)""")
    List<LateComerDto> findAllLateComersByPromiseId(Long promiseId);

    @Query("""
            SELECT CASE WHEN EXISTS (
                SELECT p FROM Participant p
                JOIN p.member m 
                JOIN m.user u 
                JOIN p.promise pr
                WHERE pr.id = :promiseId AND u.id = :userId
            ) THEN TRUE ELSE FALSE END
            FROM Participant p""")
    boolean existsByPromiseIdAndUserId(Long promiseId, Long userId);

    List<Participant> findAllByPromiseId(Long promiseId);

    @Query("""
            SELECT COUNT(p) FROM Participant p
            WHERE p.promise.id = :promiseId AND p.preparationStartAt IS NOT NULL""")
    int countFirstPreparationByPromiseId(Long promiseId);

    @Query("""
            SELECT COUNT(p) FROM Participant p
            WHERE p.promise.id = :promiseId AND p.departureAt IS NOT NULL""")
    int countFirstDepartureByPromiseId(Long promiseId);

    @Query("""
            SELECT COUNT(p) FROM Participant p
            WHERE p.promise.id = :promiseId AND p.arrivalAt IS NOT NULL""")
    int countFirstArrivalByPromiseId(Long promiseId);

    @Query("""
            SELECT ui.fcmToken
            FROM Participant p
            JOIN Member m ON p.member.id = m.id
            JOIN UserInfo ui ON m.user.id = ui.user.id
            WHERE p.promise.id = :promiseId AND m.user.id != :userId""")
    List<String> findFcmTokenByPromiseId(Long promiseId, Long userId);

    void deleteByMemberId(Long memberId);

    Participant findByMemberIdAndPromiseId(Long memberId, Long promiseId);
}
