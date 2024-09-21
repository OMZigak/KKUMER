package org.kkumulkkum.server.api.auth.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import org.kkumulkkum.server.domain.user.Provider;

public record UserLoginDto(
        @NotNull
        Provider provider,
        @Nullable
        String fcmToken
) {
}
