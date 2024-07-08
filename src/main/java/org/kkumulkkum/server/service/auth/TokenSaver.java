package org.kkumulkkum.server.service.auth;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.domain.Token;
import org.kkumulkkum.server.repository.TokenRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenSaver {

    private final TokenRepository tokenRepository;

    public Token save(final Token token){
        return tokenRepository.save(token);
    }

}
