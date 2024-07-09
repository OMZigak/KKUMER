package org.kkumulkkum.server.dto.user.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NameUpdateDto(
        @NotBlank @Size(max = 5)
        String name
) {
}
