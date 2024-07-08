package org.kkumulkkum.server.dto.meeting.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record CreatedMeetingDto(
        @JsonIgnore
        Long id,
        String invitationCode
) {
}
