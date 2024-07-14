package org.kkumulkkum.server.dto.auth.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import org.kkumulkkum.server.domain.enums.Provider;

public record UserLoginDto(
        @NotNull
        Provider provider,
        @Nullable
        String fcmToken
) {
}
