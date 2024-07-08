package org.kkumulkkum.server.dto.member;

public record MemberUserInfoDto(
        Long id,
        String name,
        String profileImg
) {
    public static MemberUserInfoDto of(Long id, String name, String profileImg) {
        return new MemberUserInfoDto(
                id,
                name,
                profileImg
        );
    }

}
