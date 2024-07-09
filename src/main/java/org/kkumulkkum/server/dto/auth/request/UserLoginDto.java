package org.kkumulkkum.server.dto.auth.request;

import jakarta.validation.constraints.NotNull;
import org.kkumulkkum.server.domain.enums.Provider;

public record UserLoginDto(
        @NotNull
        Provider provider
) {
}
