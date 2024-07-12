package org.kkumulkkum.server.repository;

import org.kkumulkkum.server.domain.Promise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface PromiseRepository extends JpaRepository<Promise, Long> {

    List<Promise> findAllByMeetingId(Long meetingId);

    @Query("SELECT p FROM Participant pt " +
            "JOIN pt.member m " +
            "JOIN pt.promise p " +
            "WHERE m.user.id = :userId " +
            "AND p.time >= :startOfDay " +
            "AND p.time < :startOfNextDay " +
            "AND p.time > CURRENT_TIMESTAMP " +
            "AND p.isCompleted = false " +
            "ORDER BY p.time ASC, p.createdAt ASC")
    List<Promise> findNextPromiseByUserId(Long userId, LocalDateTime startOfDay, LocalDateTime startOfNextDay, Pageable pageable);

    @Query("SELECT p FROM Participant pt " +
            "JOIN pt.member m " +
            "JOIN pt.promise p " +
            "WHERE m.user.id = :userId " +
            "AND p.time > CURRENT_TIMESTAMP " +
            "AND p.isCompleted = false " +
            "AND p.id <> :nextPromiseId " +
            "ORDER BY p.time ASC, p.createdAt ASC")
    Page<Promise> findUpcomingPromisesExcludingNext(Long userId, Long nextPromiseId, Pageable pageable);

    @Query("SELECT p FROM Participant pt " +
            "JOIN pt.member m " +
            "JOIN pt.promise p " +
            "WHERE m.user.id = :userId " +
            "AND p.time > CURRENT_TIMESTAMP " +
            "AND p.isCompleted = false " +
            "ORDER BY p.time ASC, p.createdAt ASC")
    Page<Promise> findUpcomingPromises(Long userId, Pageable pageable);

    @Query("SELECT CASE WHEN EXISTS " +
            "(SELECT p FROM Participant p WHERE p.promise.id = :promiseId AND p.arrivalAt IS NULL) " +
            "THEN TRUE ELSE FALSE END FROM Participant p")
    boolean existsByArrivedAtIsNull(Long promiseId);

}
