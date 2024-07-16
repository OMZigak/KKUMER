package org.kkumulkkum.server.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.annotation.UserId;
import org.kkumulkkum.server.constant.AuthConstant;
import org.kkumulkkum.server.dto.auth.request.UserLoginDto;
import org.kkumulkkum.server.dto.auth.response.JwtTokenDto;
import org.kkumulkkum.server.dto.auth.response.UserTokenDto;
import org.kkumulkkum.server.service.auth.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/v1/auth/signin")
    public ResponseEntity<UserTokenDto> signin(
            @NotNull @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) final String providerToken,
            @Valid @RequestBody final UserLoginDto userLoginDto
    ) {
        return ResponseEntity.ok(authService.signin(providerToken, userLoginDto));
    }


    @PostMapping("/v1/auth/signout")
    public ResponseEntity<Void> signout(
            @UserId final Long userId
    ) {
        authService.signout(userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/v1/auth/reissue")
    public ResponseEntity<JwtTokenDto> reissue(
            @NotNull @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) final String refreshToken
    ) {
        return ResponseEntity.ok(authService.reissue(refreshToken));
    }

}
