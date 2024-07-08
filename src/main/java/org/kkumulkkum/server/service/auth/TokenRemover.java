package org.kkumulkkum.server.service.auth;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.repository.TokenRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenRemover {

    private final TokenRepository tokenRepository;

    public void removeById(final Long id) {
        tokenRepository.deleteById(id);
    }
}
