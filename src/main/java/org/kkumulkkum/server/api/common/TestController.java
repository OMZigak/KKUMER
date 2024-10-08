package org.kkumulkkum.server.api.common;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.common.auth.jwt.JwtTokenProvider;
import org.kkumulkkum.server.api.auth.dto.response.JwtTokenDto;
import org.kkumulkkum.server.common.exception.BusinessException;
import org.kkumulkkum.server.common.exception.code.BusinessErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/v1/test/default")
    public ResponseEntity<Void> testDefault() {
        throw new RuntimeException();
    }

    @GetMapping("/v1/test/business")
    public ResponseEntity<Void> testBusiness() {
        throw new BusinessException(BusinessErrorCode.BAD_REQUEST);
    }

    @GetMapping("/v1/test/token/{userId}")
    public ResponseEntity<JwtTokenDto> testToken(
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(jwtTokenProvider.issueTokens(userId));
    }
}
