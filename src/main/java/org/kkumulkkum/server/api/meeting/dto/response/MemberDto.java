package org.kkumulkkum.server.api.meeting.dto.response;

public record MemberDto(
        Long memberId,
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
