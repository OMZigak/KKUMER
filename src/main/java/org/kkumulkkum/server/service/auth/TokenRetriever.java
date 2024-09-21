package org.kkumulkkum.server.service.auth;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.domain.user.Token;
import org.kkumulkkum.server.exception.AuthException;
import org.kkumulkkum.server.exception.code.AuthErrorCode;
import org.kkumulkkum.server.domain.user.repository.TokenRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenRetriever {

    private final TokenRepository tokenRepository;

    public Token findByRefreshToken(final String refreshToken) {
        return tokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new AuthException(AuthErrorCode.NOT_FOUND_REFRESH_TOKEN));
    }
}
