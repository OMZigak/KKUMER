package org.kkumulkkum.server.api.meeting.dto.response;

import java.util.List;

public record MembersDto(
        int memberCount,
        List<MemberDto> members
) {
    public static MembersDto from(List<MemberDto> members) {
        return new MembersDto(
                members.size(),
                members
        );
    }
}
