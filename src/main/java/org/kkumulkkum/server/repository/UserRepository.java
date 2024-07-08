package org.kkumulkkum.server.repository;

import org.kkumulkkum.server.domain.User;
import org.kkumulkkum.server.domain.enums.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByProviderIdAndProvider(String serialId, Provider provider);

    Optional<User> findByProviderIdAndProvider(String providerId, Provider provider);
}
