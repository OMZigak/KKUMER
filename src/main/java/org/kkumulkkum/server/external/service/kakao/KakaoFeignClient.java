package org.kkumulkkum.server.external.service.kakao;

import org.kkumulkkum.server.external.service.kakao.dto.KakaoUserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "kakaoFeignClient", url = "https://kapi.kakao.com")
public interface KakaoFeignClient {
    @GetMapping(value = "/v2/user/me")
    KakaoUserDto getUserInformation(@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken);

    @PostMapping(value = "/v1/user/unlink")
    void unlinkUser(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String adminKey,
            @RequestParam("target_id_type") String targetIdType,
            @RequestParam("target_id") Long targetId
    );
}