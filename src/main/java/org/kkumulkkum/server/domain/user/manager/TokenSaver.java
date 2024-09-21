package org.kkumulkkum.server.domain.user.manager;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.domain.user.Token;
import org.kkumulkkum.server.domain.user.repository.TokenRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenSaver {

    private final TokenRepository tokenRepository;

    public void save(final Token token){
        tokenRepository.save(token);
    }

}
