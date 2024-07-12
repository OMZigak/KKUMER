package org.kkumulkkum.server.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.annotation.UserId;
import org.kkumulkkum.server.dto.user.request.ImageUpdateDto;
import org.kkumulkkum.server.dto.user.request.NameUpdateDto;
import org.kkumulkkum.server.dto.user.response.UserDto;
import org.kkumulkkum.server.dto.user.response.UserNameDto;
import org.kkumulkkum.server.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    @PatchMapping("/users/me/image")
    public ResponseEntity<Void> updateImage(
            @UserId final Long userId,
            @Valid @ModelAttribute final ImageUpdateDto imageUpdateDto
    ) {
        userService.updateImage(userId, imageUpdateDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/users/me/image")
    public ResponseEntity<Void> deleteImage(
            @UserId final Long userId
    ) {
        userService.deleteImage(userId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/users/me/name")
    public ResponseEntity<UserNameDto> updateName(
            @UserId final Long userId,
            @Valid @RequestBody final NameUpdateDto nameUpdateDto
    ) {
        return ResponseEntity.ok().body(userService.updateName(userId, nameUpdateDto));
    }

    @GetMapping("/users/me")
    public ResponseEntity<UserDto> getUserInfo(
            @UserId final Long userId
    ) {
        return ResponseEntity.ok().body(userService.getUserInfo(userId));
    }
}
