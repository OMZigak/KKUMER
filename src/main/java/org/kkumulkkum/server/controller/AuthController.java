package org.kkumulkkum.server.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.constant.Constant;
import org.kkumulkkum.server.dto.auth.request.UserLoginDto;
import org.kkumulkkum.server.dto.auth.response.JwtTokenDto;
import org.kkumulkkum.server.service.auth.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/signin")
    public ResponseEntity<JwtTokenDto> signin(
            @NotNull @RequestHeader(Constant.AUTHORIZATION_HEADER) final String providerToken,
            @Valid @RequestBody final UserLoginDto userLoginDto
    ) {
        return ResponseEntity.ok(authService.signin(providerToken, userLoginDto));
    }

    @PostMapping("/auth/reissue")
    public ResponseEntity<JwtTokenDto> reissue(
            @NotNull @RequestHeader(Constant.AUTHORIZATION_HEADER) final String refreshToken
    ) {
        return ResponseEntity.ok(authService.reissue(refreshToken));
    }

}
