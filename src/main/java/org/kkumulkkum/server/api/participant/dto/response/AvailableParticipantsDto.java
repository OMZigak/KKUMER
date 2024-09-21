package org.kkumulkkum.server.api.participant.dto.response;

import org.kkumulkkum.server.api.meeting.dto.response.MemberDto;

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
                        //모임 내 멤버가 약속 참여중인 멤버 리스트 안에 있으면 true, 아니면 false
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
