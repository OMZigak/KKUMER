package org.kkumulkkum.server.controller;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.annotation.UserId;
import org.kkumulkkum.server.dto.participant.response.ParticipantsDto;
import org.kkumulkkum.server.dto.participant.response.PreparationStatusDto;
import org.kkumulkkum.server.service.participant.ParticipantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ParticipantController {

    private final ParticipantService participantService;

    @PatchMapping("/promises/{promiseId}/preperation")
    public ResponseEntity<Void> preparePromise(
            @UserId Long userId,
            @PathVariable Long promiseId
    ) {
        participantService.preparePromise(userId, promiseId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/promises/{promiseId}/departure")
    public ResponseEntity<Void> departurePromise(
            @UserId Long userId,
            @PathVariable Long promiseId
    ) {
        participantService.departurePromise(userId, promiseId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/promises/{promiseId}/arrival")
    public ResponseEntity<Void> arrivalPromise(
            @UserId Long userId,
            @PathVariable Long promiseId
    ) {
        participantService.arrivalPromise(userId, promiseId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/promises/{promiseId}/status")
    public ResponseEntity<PreparationStatusDto> getPreparationStatus(
            @UserId final Long userId,
            @PathVariable("promiseId") final Long promiseId
    ) {
        return ResponseEntity.ok().body(participantService.getPreparation(userId, promiseId));
    }

    @GetMapping("/promises/{promiseId}/participants")
    public ResponseEntity<ParticipantsDto> getParticipants(
            @UserId final Long userId,
            @PathVariable("promiseId") final Long promiseId
    ) {
        return ResponseEntity.ok().body(participantService.getParticipants(userId, promiseId));
    }


}
