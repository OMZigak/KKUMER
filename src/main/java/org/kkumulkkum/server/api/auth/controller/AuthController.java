package org.kkumulkkum.server.api.auth.controller;

import com.google.firebase.database.annotations.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.annotation.UserId;
import org.kkumulkkum.server.constant.AuthConstant;
import org.kkumulkkum.server.api.auth.dto.request.UserLoginDto;
import org.kkumulkkum.server.api.auth.dto.response.JwtTokenDto;
import org.kkumulkkum.server.api.auth.dto.response.UserTokenDto;
import org.kkumulkkum.server.api.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/v1/auth/signin")
    public ResponseEntity<UserTokenDto> signin(
            @NotBlank @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) final String providerToken,
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
            @NotBlank @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) final String refreshToken
    ) {
        return ResponseEntity.ok(authService.reissue(refreshToken));
    }

    @DeleteMapping("/v1/auth/withdrawal")
    public ResponseEntity<Void> withdraw(
            @UserId final Long userId,
            @Nullable @RequestHeader(value = AuthConstant.APPLE_WITHDRAW_HEADER, required = false) final String authCode
    ) {
        authService.withdrawal(userId, authCode);
        return ResponseEntity.ok().build();
    }
}
