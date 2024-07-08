package org.kkumulkkum.server.dto.member.response;

import java.util.List;

public record MembersDto(
        int memberCount,
        List<MemberDto> members
) {
    public static MembersDto of(List<MemberDto> members) {
        return new MembersDto(
                members.size(),
                members
        );
    }
}
