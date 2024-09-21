package org.kkumulkkum.server.api.auth.dto.response;

import org.kkumulkkum.server.domain.userinfo.UserInfo;

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
