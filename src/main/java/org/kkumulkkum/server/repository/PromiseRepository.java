package org.kkumulkkum.server.repository;

import org.kkumulkkum.server.domain.Promise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PromiseRepository extends JpaRepository<Promise, Long> {

    List<Promise> findAllByMeetingId(Long meetingId);
}
