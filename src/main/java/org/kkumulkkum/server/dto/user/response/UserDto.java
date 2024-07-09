package org.kkumulkkum.server.dto.user.response;

import org.kkumulkkum.server.domain.UserInfo;

public record UserDto(
        String name,
        int level,
        int promiseCount,
        int tardyCount,
        Long tardySum,
        String profileImg
) {
    public static UserDto from(UserInfo userInfo) {
        return new UserDto(
                userInfo.getName(),
                userInfo.getLevel(),
                userInfo.getPromiseCount(),
                userInfo.getTardyCount(),
                userInfo.getTardySum(),
                userInfo.getProfileImg()
        );
    }
}
