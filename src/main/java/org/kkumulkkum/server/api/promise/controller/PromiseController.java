package org.kkumulkkum.server.api.promise.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.annotation.IsMemberByMeetingId;
import org.kkumulkkum.server.annotation.IsMemberByPromiseId;
import org.kkumulkkum.server.annotation.IsParticipant;
import org.kkumulkkum.server.annotation.UserId;
import org.kkumulkkum.server.api.promise.dto.request.PromiseCreateDto;
import org.kkumulkkum.server.api.promise.dto.response.*;
import org.kkumulkkum.server.api.promise.service.PromiseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PromiseController {

    private final PromiseService promiseService;

    @IsMemberByMeetingId(meetingIdParamIndex = 1)
    @PostMapping("/v1/meetings/{meetingId}/promises")
    public ResponseEntity<PromiseAddDto> createPromise(
            @UserId final Long userId,
            @PathVariable final Long meetingId,
            @Valid @RequestBody final PromiseCreateDto createPromiseDto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(promiseService.createPromise(userId, meetingId, createPromiseDto));
    }

    @IsParticipant(promiseIdParamIndex = 0)
    @PatchMapping("/v1/promises/{promiseId}/completion")
    public ResponseEntity<Void> completePromise(
            @PathVariable final Long promiseId
    ) {
        promiseService.completePromise(promiseId);
        return ResponseEntity.ok().build();
    }

    @IsMemberByMeetingId(meetingIdParamIndex = 1)
    @GetMapping("/v1/meetings/{meetingId}/promises")
    public ResponseEntity<PromisesDto> getPromises(
            @UserId final Long userId,
            @PathVariable("meetingId") final Long meetingId,
            @RequestParam(required = false) final Boolean done,
            @RequestParam(required = false) final Boolean isParticipant
    ) {
        return ResponseEntity.ok().body(promiseService.getPromises(userId, meetingId, done, isParticipant));
    }

    @IsMemberByPromiseId(promiseIdParamIndex = 1)
    @GetMapping("/v1/promises/{promiseId}")
    public ResponseEntity<PromiseDetailDto> getPromise(
            @UserId final Long userId,
            @PathVariable("promiseId") final Long promiseId
    ) {
        return ResponseEntity.ok().body(promiseService.getPromise(userId, promiseId));
    }

    @GetMapping("/v1/promises/today/next")
    public ResponseEntity<MainPromiseDto> getNextPromise(
            @UserId final Long userId
    ) {
        return ResponseEntity.ok().body(promiseService.getNextPromise(userId));
    }

    @GetMapping("/v1/promises/upcoming")
    public ResponseEntity<MainPromisesDto> getUpcomingPromise(
            @UserId final Long userId
    ) {
        return ResponseEntity.ok().body(promiseService.getUpcomingPromises(userId));
    }

    @IsParticipant(promiseIdParamIndex = 1)
    @PutMapping("/v1/promises/{promiseId}")
    public ResponseEntity<PromiseAddDto> updatePromise(
            @UserId final Long userId,
            @PathVariable final Long promiseId,
            @Valid @RequestBody final PromiseCreateDto updatePromiseDto
    ) {
        return ResponseEntity.ok(promiseService.updatePromise(userId, promiseId, updatePromiseDto));
    }

    @IsParticipant(promiseIdParamIndex = 0)
    @DeleteMapping("/v1/promises/{promiseId}")
    public ResponseEntity<Void> deletePromise(
            @PathVariable("promiseId") final Long promiseId
    ) {
        promiseService.deletePromise(promiseId);
        return ResponseEntity.ok().build();
    }
}
