package org.kkumulkkum.server.api.auth.dto.verify;

public record ApplePublicKey(
        String kty, String kid, String use, String alg, String n, String e, String email
) {

}
