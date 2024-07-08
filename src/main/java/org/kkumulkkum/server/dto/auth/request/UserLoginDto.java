package org.kkumulkkum.server.dto.auth.request;

import org.kkumulkkum.server.domain.enums.Provider;

public record UserLoginDto(
        Provider provider
) {
}
