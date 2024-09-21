package org.kkumulkkum.server.domain.user.repository;

import org.kkumulkkum.server.domain.user.Token;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TokenRepository extends CrudRepository<Token, Long> {

    Optional<Token> findByRefreshToken(String refreshToken);
}
