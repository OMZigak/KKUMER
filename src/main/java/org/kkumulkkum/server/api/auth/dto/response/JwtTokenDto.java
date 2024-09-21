package org.kkumulkkum.server.api.auth.dto.response;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record JwtTokenDto(
        String accessToken,
        String refreshToken
) {
    public static JwtTokenDto of(String accessToken, String refreshToken) {
        return JwtTokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
