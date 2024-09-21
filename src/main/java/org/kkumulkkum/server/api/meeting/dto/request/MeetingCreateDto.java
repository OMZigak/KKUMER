package org.kkumulkkum.server.api.meeting.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MeetingCreateDto(
        @NotBlank @Size(max = 10)
        String name
) {
}
