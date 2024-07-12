package org.kkumulkkum.server.dto.auth.response;

import org.kkumulkkum.server.domain.UserInfo;

public record UserTokenDto(
        String name,
        JwtTokenDto jwtTokenDto
) {
    public static UserTokenDto of(UserInfo userInfo, JwtTokenDto tokens) {
        return new UserTokenDto(
                userInfo.getName(),
                tokens
        );
    }
}
