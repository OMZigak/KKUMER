package org.kkumulkkum.server.common.auth.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.common.exception.AuthException;
import org.kkumulkkum.server.common.exception.code.AuthErrorCode;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenValidator {

    private final JwtTokenProvider jwtTokenProvider;

    public void validateAccessToken(String accessToken) {
        try {
            jwtTokenProvider.getBody(accessToken);
        } catch (MalformedJwtException ex) {
            throw new AuthException(AuthErrorCode.INVALID_TOKEN);
        } catch (ExpiredJwtException ex) {
            throw new AuthException(AuthErrorCode.EXPIRED_TOKEN);
        } catch (UnsupportedJwtException ex) {
            throw new AuthException(AuthErrorCode.UNSUPPORTED_TOKEN);
        } catch (IllegalArgumentException ex) {
            throw new AuthException(AuthErrorCode.EMPTY_TOKEN);
        }
    }

    public void equalsRefreshToken(String refreshToken, String storedRefreshToken) {
        if (!refreshToken.equals(storedRefreshToken)) {
            throw new AuthException(AuthErrorCode.MISMATCH_REFRESH_TOKEN);
        }
    }
}
