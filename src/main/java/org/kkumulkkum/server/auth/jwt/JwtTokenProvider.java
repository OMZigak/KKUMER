package org.kkumulkkum.server.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.dto.auth.response.JwtTokenDto;
import org.kkumulkkum.server.exception.AuthException;
import org.kkumulkkum.server.exception.code.AuthErrorCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    static final String USER_ID = "userId";

    private static final Long ACCESS_TOKEN_EXPIRATION_TIME = 24 * 60 * 60 * 1000L;

    private static final Long REFRESH_TOKEN_EXPIRATION_TIME = 24 * 60 * 60 * 1000L * 14;

    @Value("${jwt.secret}")
    private String secretKey;

    public JwtTokenDto issueToken(Long userId) {
        return JwtTokenDto.of(
                generateToken(userId, true),
                generateToken(userId, false));
    }

     public String generateToken(Long userId, boolean isAccessToken) {
         final Date now = new Date();
         final Date expirationDate = generateExpirationDate(now, isAccessToken);
         final Claims claims = Jwts.claims()
                 .setIssuedAt(now)
                 .setExpiration(expirationDate);

         claims.put(USER_ID, userId);

         return Jwts.builder()
                 .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                 .setClaims(claims)
                 .signWith(getSigningKey())
                 .compact();
     }

     private Date generateExpirationDate(Date now, boolean isAccessToken) {
         if (isAccessToken) {
             return new Date(now.getTime() + ACCESS_TOKEN_EXPIRATION_TIME);
         }
         return new Date(now.getTime() + REFRESH_TOKEN_EXPIRATION_TIME);
     }

     private Key getSigningKey() {
         String encodedKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
         return Keys.hmacShaKeyFor(encodedKey.getBytes());
     }

    public Claims getBody(final String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Long getUserIdFromJwt(String token) {
        Claims claims = getBody(token);
        return Long.valueOf(claims.get(USER_ID).toString());
    }

    public static Object checkPrincipal(final Object principal) {
        if ("anonymousUser".equals(principal)) {
            throw new AuthException(AuthErrorCode.UNAUTHORIZED);
        }
        return principal;
    }


}
