package org.kkumulkkum.server.dto.user.response;

import org.kkumulkkum.server.domain.userinfo.UserInfo;

public record UserDto(
        Long userId,
        String name,
        int level,
        int promiseCount,
        int tardyCount,
        Long tardySum,
        String profileImg
) {
    public static UserDto from(UserInfo userInfo) {
        return new UserDto(
                userInfo.getUser().getId(),
                userInfo.getName(),
                userInfo.getLevel(),
                userInfo.getPromiseCount(),
                userInfo.getTardyCount(),
                userInfo.getTardySum()/60,
                userInfo.getProfileImg()
        );
    }
}
