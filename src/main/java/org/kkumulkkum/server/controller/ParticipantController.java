package org.kkumulkkum.server.controller;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.annotation.UserId;
import org.kkumulkkum.server.service.participant.ParticipantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
