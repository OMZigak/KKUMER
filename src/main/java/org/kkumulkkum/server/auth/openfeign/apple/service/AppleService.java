package org.kkumulkkum.server.auth.openfeign.apple.service;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kkumulkkum.server.auth.openfeign.apple.AppleFeignClient;
import org.kkumulkkum.server.auth.openfeign.apple.dto.ApplePublicKeys;
import org.kkumulkkum.server.auth.openfeign.apple.dto.AppleTokenDto;
import org.kkumulkkum.server.auth.openfeign.apple.verify.AppleClientSecretGenerator;
import org.kkumulkkum.server.auth.openfeign.apple.verify.AppleJwtParser;
import org.kkumulkkum.server.auth.openfeign.apple.verify.PublicKeyGenerator;
import org.kkumulkkum.server.auth.openfeign.kakao.dto.SocialUserDto;
import org.kkumulkkum.server.exception.AuthException;
import org.kkumulkkum.server.exception.code.AuthErrorCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppleService {

    private final String APPLE_SUBJECT = "sub";
    private final String APPLE_EMAIL = "email";

    private final AppleFeignClient appleFeignClient;
    private final AppleJwtParser appleJwtParser;
    private final PublicKeyGenerator publicKeyGenerator;
    private final AppleClientSecretGenerator appleClientSecretGenerator;

    @Value("${oauth.apple.client-id}")
    private String clientId;

    public SocialUserDto getSocialUserInfo(String identityToken) {

        Map<String, String> headers = appleJwtParser.parseHeaders(identityToken);
        ApplePublicKeys applePublicKeys = appleFeignClient.getApplePublicKeys();
        PublicKey publicKey = publicKeyGenerator.generatePublicKey(headers, applePublicKeys);
        Claims claims = appleJwtParser.parsePublicKeyAndGetClaims(identityToken, publicKey);
        return SocialUserDto.of(claims.get(APPLE_SUBJECT, String.class), claims.get(APPLE_EMAIL, String.class));
    }

    public void revoke(final String authCode) {
        try {
            String clientSecret = appleClientSecretGenerator.createClientSecret();
            String refreshToken = getRefreshToken(authCode, clientSecret);
            appleFeignClient.revoke(
                    clientId,
                    clientSecret,
                    refreshToken,
                    "refresh_token"
            );
        } catch (Exception e){
            log.error("apple revoke failed : {}", e.getMessage());
            throw new AuthException(AuthErrorCode.APPLE_REVOKE_FAILED);
        }
    }

    private String getRefreshToken(final String authCode, final String clientSecret) {
        try {
            AppleTokenDto appleTokenDto = appleFeignClient.getAppleToken(
                    clientId,
                    clientSecret,
                    "authorization_code",
                    authCode
            );
            return appleTokenDto.refreshToken();
        } catch (Exception e){
            log.error("apple token request failed : {}", e.getMessage());
            throw new AuthException(AuthErrorCode.APPLE_TOKEN_REQUEST_FAILED);
        }
    }
}
