package org.kkumulkkum.server.repository;

import org.kkumulkkum.server.domain.Token;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TokenRepository extends CrudRepository<Token, Long> {

    Optional<Token> findIdByRefreshToken(String refreshToken);
}
