package org.kkumulkkum.server.auth.openfeign.kakao.service;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.auth.openfeign.kakao.KakaoFeignClient;
import org.kkumulkkum.server.auth.openfeign.kakao.dto.KakaoUserDto;
import org.kkumulkkum.server.auth.openfeign.kakao.dto.SocialUserDto;
import org.kkumulkkum.server.constant.Constant;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KakaoService {

    private final KakaoFeignClient kakaoFeignClient;
    public SocialUserDto getSocialUserInfo(String providerToken) {
        KakaoUserDto kakaoUserDto = kakaoFeignClient.getUserInformation(Constant.BEARER_TOKEN_PREFIX + providerToken);
        return SocialUserDto.of(
                kakaoUserDto.id().toString(),
                kakaoUserDto.kakaoAccount().email());
    }
}
