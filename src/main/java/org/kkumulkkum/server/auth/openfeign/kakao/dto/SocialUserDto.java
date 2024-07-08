package org.kkumulkkum.server.auth.openfeign.kakao.dto;

public record SocialUserDto(String platformId, String email) {

    public static SocialUserDto of(String platformId, String email) {
        return new SocialUserDto(platformId, email);
    }
}
