package org.kkumulkkum.server.api.user.dto.response;

import org.kkumulkkum.server.domain.userinfo.UserInfo;

public record UserNameDto(
        String name
) {
    public static UserNameDto from(UserInfo userInfo) {
        return new UserNameDto(userInfo.getName());
    }
}
