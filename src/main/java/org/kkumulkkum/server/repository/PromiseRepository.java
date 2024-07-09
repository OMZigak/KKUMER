package org.kkumulkkum.server.repository;

import org.kkumulkkum.server.domain.Promise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PromiseRepository extends JpaRepository<Promise, Long> {

    @Query("SELECT p FROM Promise p JOIN p.meeting m WHERE m.id = :meetingId")
    List<Promise> findAllByMeetingId(Long meetingId);
}
