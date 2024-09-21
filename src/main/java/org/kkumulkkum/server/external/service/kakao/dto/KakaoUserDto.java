package org.kkumulkkum.server.external.service.kakao.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record KakaoUserDto(
        Long id,
        KakaoAccount kakaoAccount
) {
}
