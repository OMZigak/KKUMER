package org.kkumulkkum.server.auth.openfeign.apple.service;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.auth.openfeign.apple.AppleFeignClient;
import org.kkumulkkum.server.auth.openfeign.apple.dto.ApplePublicKeys;
import org.kkumulkkum.server.auth.openfeign.apple.verify.AppleJwtParser;
import org.kkumulkkum.server.auth.openfeign.apple.verify.PublicKeyGenerator;
import org.kkumulkkum.server.auth.openfeign.kakao.dto.SocialUserDto;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AppleService {

    private final AppleFeignClient appleFeignClient;
    private final AppleJwtParser appleJwtParser;
    private final PublicKeyGenerator publicKeyGenerator;
    public SocialUserDto getSocialUserInfo(String identityToken) {

        Map<String, String> headers = appleJwtParser.parseHeaders(identityToken);
        ApplePublicKeys applePublicKeys = appleFeignClient.getApplePublicKeys();
        PublicKey publicKey = publicKeyGenerator.generatePublicKey(headers, applePublicKeys);
        Claims claims = appleJwtParser.parsePublicKeyAndGetClaims(identityToken, publicKey);
        return SocialUserDto.of(claims.get("sub", String.class), claims.get("email", String.class));
    }

}
