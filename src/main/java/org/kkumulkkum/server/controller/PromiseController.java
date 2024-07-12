package org.kkumulkkum.server.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.annotation.IsMember;
import org.kkumulkkum.server.annotation.UserId;
import org.kkumulkkum.server.dto.promise.PromiseCreateDto;
import org.kkumulkkum.server.dto.promise.response.MainPromiseDto;
import org.kkumulkkum.server.dto.promise.response.MainPromisesDto;
import org.kkumulkkum.server.dto.promise.response.PromiseDto;
import org.kkumulkkum.server.dto.promise.response.PromisesDto;
import org.kkumulkkum.server.service.promise.PromiseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PromiseController {

    private final PromiseService promiseService;

    @IsMember(meetingIdParamIndex = 1)
    @PostMapping("/meetings/{meetingId}/promises")
    public ResponseEntity<Void> createPromise(
            @UserId Long userId,
            @PathVariable Long meetingId,
            @Valid @RequestBody PromiseCreateDto createPromiseDto
    ) {
        Long promiseId = promiseService.createPromise(userId, meetingId, createPromiseDto);
        return ResponseEntity.created(URI.create(promiseId.toString())).build();
    }

    @PatchMapping("/promises/{promiseId}/completion")
    public ResponseEntity<Void> completePromise(
            @UserId Long userId,
            @PathVariable Long promiseId
    ) {
        promiseService.completePromise(userId, promiseId);
        return ResponseEntity.ok().build();
    }

    @IsMember(meetingIdParamIndex = 1)
    @GetMapping("/meetings/{meetingId}/promises")
    public ResponseEntity<PromisesDto> getPromises(
            @PathVariable("meetingId") final Long meetingId,
            @RequestParam(required = false) final Boolean done
    ) {
        return ResponseEntity.ok().body(promiseService.getPromises(meetingId, done));
    }

    @IsMember(meetingIdParamIndex = 1)
    @GetMapping("/promises/{promiseId}")
    public ResponseEntity<PromiseDto> getPromise(
            @PathVariable("promiseId") final Long promiseId
    ) {
        return ResponseEntity.ok().body(promiseService.getPromise(promiseId));
    }

    @GetMapping("/promises/today/next")
    public ResponseEntity<MainPromiseDto> getNextPromise(
            @UserId final Long userId
    ) {
        return ResponseEntity.ok().body(promiseService.getNextPromise(userId));
    }

    @GetMapping("/promises/upcoming")
    public ResponseEntity<MainPromisesDto> getUpcomingPromise(
            @UserId final Long userId
    ) {
        return ResponseEntity.ok().body(promiseService.getUpcomingPromises(userId));
    }
}
