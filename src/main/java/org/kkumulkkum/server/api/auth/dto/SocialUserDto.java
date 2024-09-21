package org.kkumulkkum.server.api.auth.dto;

public record SocialUserDto(String platformId, String email) {

    public static SocialUserDto of(String platformId, String email) {
        return new SocialUserDto(platformId, email);
    }
}
