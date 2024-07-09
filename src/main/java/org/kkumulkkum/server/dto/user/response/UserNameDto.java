package org.kkumulkkum.server.dto.user.response;

import org.kkumulkkum.server.domain.UserInfo;

public record UserNameDto(
        String name
) {
    public static UserNameDto from(UserInfo userInfo) {
        return new UserNameDto(userInfo.getName());
    }
}
