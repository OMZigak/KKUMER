package org.kkumulkkum.server.external.service.apple.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record AppleTokenDto(
        String accessToken,
        String tokenType,
        String expiresIn,
        String refreshToken,
        String idToken
) {
}
