package org.kkumulkkum.server.repository;

import org.kkumulkkum.server.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
