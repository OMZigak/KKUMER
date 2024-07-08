package org.kkumulkkum.server.dto.member.response;

public record MemberDto(
        Long id,
        String name,
        String profileImg
) {
    public static MemberDto of(Long id, String name, String profileImg) {
        return new MemberDto(
                id,
                name,
                profileImg
        );
    }
}
