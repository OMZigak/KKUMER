package org.kkumulkkum.server.api.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.annotation.UserId;
import org.kkumulkkum.server.api.user.dto.request.ImageUpdateDto;
import org.kkumulkkum.server.api.user.dto.request.NameUpdateDto;
import org.kkumulkkum.server.api.user.dto.response.UserDto;
import org.kkumulkkum.server.api.user.dto.response.UserNameDto;
import org.kkumulkkum.server.api.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PatchMapping("/v1/users/me/image")
    public ResponseEntity<Void> updateImage(
            @UserId final Long userId,
            @Valid @ModelAttribute final ImageUpdateDto imageUpdateDto
    ) {
        userService.updateImage(userId, imageUpdateDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/v1/users/me/image")
    public ResponseEntity<Void> deleteImage(
            @UserId final Long userId
    ) {
        userService.deleteImage(userId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/v1/users/me/name")
    public ResponseEntity<UserNameDto> updateName(
            @UserId final Long userId,
            @Valid @RequestBody final NameUpdateDto nameUpdateDto
    ) {
        return ResponseEntity.ok().body(userService.updateName(userId, nameUpdateDto));
    }

    @GetMapping("/v1/users/me")
    public ResponseEntity<UserDto> getUserInfo(
            @UserId final Long userId
    ) {
        return ResponseEntity.ok().body(userService.getUserInfo(userId));
    }
}
