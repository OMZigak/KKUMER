package org.kkumulkkum.server.dto.participant.response;

import org.kkumulkkum.server.dto.member.response.MemberDto;

import java.util.List;
public record AvailableParticipantsDto(
        List<AvailableParticipantDto> members
) {
    public static AvailableParticipantsDto of(List<MemberDto> members, List<Long> participantIds) {
        List<AvailableParticipantDto> participantDtos = members.stream()
                .map(member -> new AvailableParticipantDto(
                        member.memberId(),
                        member.name(),
                        member.profileImg(),
                        participantIds.contains(member.memberId())
                )).toList();
        return new AvailableParticipantsDto(participantDtos);
    }

    public record AvailableParticipantDto(
            Long memberId,
            String name,
            String profileImg,
            boolean isParticipant
    ) {
        public static AvailableParticipantDto of(Long memberId, String name, String profileImg, boolean isParticipant) {
            return new AvailableParticipantDto(
                    memberId,
                    name,
                    profileImg,
                    isParticipant
            );
        }
    }
}
