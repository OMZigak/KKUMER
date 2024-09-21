package org.kkumulkkum.server.external.service.apple;

import org.kkumulkkum.server.api.auth.dto.verify.ApplePublicKeys;
import org.kkumulkkum.server.external.service.apple.dto.AppleTokenDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "apple-public-key-client", url = "https://appleid.apple.com/auth")
public interface AppleFeignClient {

    @GetMapping("/keys")
    ApplePublicKeys getApplePublicKeys();

    @PostMapping(value = "/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    AppleTokenDto getAppleToken(
            @RequestParam("client_id") String clientId,
            @RequestParam("client_secret") String clientSecret,
            @RequestParam("grant_type") String grantType,
            @RequestParam("code") String authCode
    );

    @PostMapping(value = "/revoke", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    void revoke(
            @RequestParam(value = "client_id") String client_id,
            @RequestParam(value = "client_secret") String client_secret,
            @RequestParam(value = "token") String token,
            @RequestParam(value = "token_type_hint") String token_type_hint
    );
}
