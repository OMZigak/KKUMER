package org.kkumulkkum.server.controller;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.auth.jwt.JwtTokenProvider;
import org.kkumulkkum.server.dto.auth.response.JwtTokenDto;
import org.kkumulkkum.server.dto.test.TestDto;
import org.kkumulkkum.server.exception.BusinessException;
import org.kkumulkkum.server.exception.code.BusinessErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TestController {

    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/test/void")
    public ResponseEntity<Void> testVoid() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/test/dto")
    public ResponseEntity<TestDto> testDto() {
        return ResponseEntity.ok(new TestDto("test"));
    }

    @GetMapping("/test/default")
    public ResponseEntity<Void> testDefault() {
        throw new RuntimeException();
    }

    @GetMapping("/test/business")
    public ResponseEntity<Void> testBusiness() {
        throw new BusinessException(BusinessErrorCode.BAD_REQUEST);
    }

    @GetMapping("/test/token/{userId}")
    public ResponseEntity<JwtTokenDto> testToken(
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(jwtTokenProvider.issueTokens(userId));
    }
}
