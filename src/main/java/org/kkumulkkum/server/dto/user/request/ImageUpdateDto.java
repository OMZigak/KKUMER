package org.kkumulkkum.server.dto.user.request;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record ImageUpdateDto(
        @NotNull
        MultipartFile image
) {
}
