package org.kkumulkkum.server.api.auth.dto.verify;

import org.kkumulkkum.server.exception.AuthException;
import org.kkumulkkum.server.exception.code.AuthErrorCode;

import java.util.List;

public record ApplePublicKeys(List<ApplePublicKey> keys) {
    public ApplePublicKey getMatchesKey(String alg, String kid) {
        return this.keys
                .stream()
                .filter(k -> k.alg().equals(alg) && k.kid().equals(kid))
                .findFirst()
                .orElseThrow(() -> new AuthException(AuthErrorCode.INVALID_APPLE_PUBLIC_KEY));
    }
}
