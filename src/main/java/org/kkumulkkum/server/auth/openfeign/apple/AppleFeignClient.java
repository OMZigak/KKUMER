package org.kkumulkkum.server.auth.openfeign.apple;

import org.kkumulkkum.server.auth.openfeign.apple.dto.ApplePublicKeys;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "apple-public-key-client", url = "https://appleid.apple.com/auth")
public interface AppleFeignClient {

    @GetMapping("/keys")
    ApplePublicKeys getApplePublicKeys();
}
