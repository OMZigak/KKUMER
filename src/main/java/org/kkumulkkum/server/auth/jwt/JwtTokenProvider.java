package org.kkumulkkum.server.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.kkumulkkum.server.constant.AuthConstant;
import org.kkumulkkum.server.api.auth.dto.response.JwtTokenDto;
import org.kkumulkkum.server.exception.AuthException;
import org.kkumulkkum.server.exception.code.AuthErrorCode;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider implements InitializingBean {

    private static final String ANONYMOUS_USER = "anonymousUser";

    @Value("${jwt.access_token_expiration_time}")
    private Long accessTokenExpirationTime;
    @Value("${jwt.refresh_token_expiration_time}")
    private Long refreshTokenExpirationTime;
    @Value("${jwt.secret}")
    private String secretKey;

    private Key singingKey;

    @Override
    public void afterPropertiesSet() throws Exception {
        String encodedKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        this.singingKey = Keys.hmacShaKeyFor(encodedKey.getBytes());
    }

    public JwtTokenDto issueTokens(Long userId) {
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

         claims.put(AuthConstant.USER_ID, userId);

         return Jwts.builder()
                 .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                 .setClaims(claims)
                 .signWith(singingKey)
                 .compact();
     }

     private Date generateExpirationDate(Date now, boolean isAccessToken) {
         if (isAccessToken) {
             return new Date(now.getTime() + accessTokenExpirationTime);
         }
         return new Date(now.getTime() + refreshTokenExpirationTime);
     }

    public Claims getBody(final String token) {
        return Jwts.parserBuilder()
                .setSigningKey(singingKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Long getUserIdFromJwt(String token) {
        Claims claims = getBody(token);
        return Long.valueOf(claims.get(AuthConstant.USER_ID).toString());
    }

    public static Object checkPrincipal(final Object principal) {
        if (ANONYMOUS_USER.equals(principal)) {
            throw new AuthException(AuthErrorCode.UNAUTHORIZED);
        }
        return principal;
    }


}
