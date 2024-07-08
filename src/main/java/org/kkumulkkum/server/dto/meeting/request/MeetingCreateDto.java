package org.kkumulkkum.server.dto.meeting.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MeetingCreateDto(
        @NotBlank @Size(max = 10)
        String name
) {
}
