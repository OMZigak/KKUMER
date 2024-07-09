package org.kkumulkkum.server.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.annotation.UserId;
import org.kkumulkkum.server.dto.promise.PromiseCreateDto;
import org.kkumulkkum.server.service.promise.PromiseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PromiseController {

    private final PromiseService promiseService;

    @PostMapping("/meetings/{meetingId}/promises")
    public ResponseEntity<Void> createPromise(
            @UserId Long userId,
            @PathVariable Long meetingId,
            @Valid @RequestBody PromiseCreateDto createPromiseDto
    ) {
        Long promiseId = promiseService.createPromise(userId, meetingId, createPromiseDto);
        return ResponseEntity.created(URI.create(promiseId.toString())).build();
    }

}
