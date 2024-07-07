package org.kkumulkkum.server.repository;

import org.kkumulkkum.server.domain.Promise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromiseRepository extends JpaRepository<Promise, Integer> {
}
