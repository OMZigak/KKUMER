package org.kkumulkkum.server.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.annotation.IsMemberByMeetingId;
import org.kkumulkkum.server.annotation.IsParticipant;
import org.kkumulkkum.server.annotation.UserId;
import org.kkumulkkum.server.dto.promise.PromiseCreateDto;
import org.kkumulkkum.server.dto.promise.response.MainPromiseDto;
import org.kkumulkkum.server.dto.promise.response.MainPromisesDto;
import org.kkumulkkum.server.dto.promise.response.PromiseDto;
import org.kkumulkkum.server.dto.promise.response.PromisesDto;
import org.kkumulkkum.server.service.promise.PromiseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class PromiseController {

    private final PromiseService promiseService;

    @IsMemberByMeetingId(meetingIdParamIndex = 1)
    @PostMapping("/v1/meetings/{meetingId}/promises")
    public ResponseEntity<PromiseDto> createPromise(
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

    @IsMemberByMeetingId(meetingIdParamIndex = 0)
    @GetMapping("/v1/meetings/{meetingId}/promises")
    public ResponseEntity<PromisesDto> getPromises(
            @PathVariable("meetingId") final Long meetingId,
            @RequestParam(required = false) final Boolean done
    ) {
        return ResponseEntity.ok().body(promiseService.getPromises(meetingId, done));
    }

    @IsMemberByMeetingId(meetingIdParamIndex = 1)
    @GetMapping("/v1/promises/{promiseId}")
    public ResponseEntity<PromiseDto> getPromise(
            @PathVariable("promiseId") final Long promiseId
    ) {
        return ResponseEntity.ok().body(promiseService.getPromise(promiseId));
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
}
