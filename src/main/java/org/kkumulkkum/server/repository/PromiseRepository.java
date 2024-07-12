package org.kkumulkkum.server.repository;

import org.kkumulkkum.server.domain.Promise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PromiseRepository extends JpaRepository<Promise, Long> {

    List<Promise> findAllByMeetingId(Long meetingId);

    @Query("SELECT CASE WHEN EXISTS " +
            "(SELECT p FROM Participant p WHERE p.promise.id =: promiseId AND p.arrivalAt IS NULL) " +
            "THEN TRUE ELSE FALSE END FROM Participant p")
    boolean existsByArrivedAtIsNull(Long promiseId);
}
