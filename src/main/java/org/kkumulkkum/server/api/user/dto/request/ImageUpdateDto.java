package org.kkumulkkum.server.api.user.dto.request;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record ImageUpdateDto(
        @NotNull
        MultipartFile image
) {
}
