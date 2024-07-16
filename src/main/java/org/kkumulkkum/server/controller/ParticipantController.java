package org.kkumulkkum.server.controller;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.annotation.IsMemberByPromiseId;
import org.kkumulkkum.server.annotation.UserId;
import org.kkumulkkum.server.dto.participant.request.PreparationInfoDto;
import org.kkumulkkum.server.dto.participant.response.LateComersDto;
import org.kkumulkkum.server.dto.participant.response.ParticipantsDto;
import org.kkumulkkum.server.dto.participant.response.PreparationStatusDto;
import org.kkumulkkum.server.service.participant.ParticipantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ParticipantController {

    private final ParticipantService participantService;

    @PatchMapping("/v1/promises/{promiseId}/preparation")
    public ResponseEntity<Void> preparePromise(
            @UserId final Long userId,
            @PathVariable final Long promiseId
    ) {
        participantService.preparePromise(userId, promiseId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/v1/promises/{promiseId}/departure")
    public ResponseEntity<Void> departurePromise(
            @UserId final Long userId,
            @PathVariable final Long promiseId
    ) {
        participantService.departurePromise(userId, promiseId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/v1/promises/{promiseId}/arrival")
    public ResponseEntity<Void> arrivalPromise(
            @UserId final Long userId,
            @PathVariable final Long promiseId
    ) {
        participantService.arrivalPromise(userId, promiseId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/promises/{promiseId}/status")
    public ResponseEntity<PreparationStatusDto> getPreparationStatus(
            @UserId final Long userId,
            @PathVariable("promiseId") final Long promiseId
    ) {
        return ResponseEntity.ok().body(participantService.getPreparation(userId, promiseId));
    }

    @IsMemberByPromiseId(promiseIdParamIndex = 0)
    @GetMapping("/v1/promises/{promiseId}/participants")
    public ResponseEntity<ParticipantsDto> getParticipants(
            @PathVariable("promiseId") final Long promiseId
    ) {
        return ResponseEntity.ok().body(participantService.getParticipants(promiseId));
    }

    @PatchMapping("/v1/promises/{promiseId}/times")
    public ResponseEntity<Void> inputPreparationInfo(
            @UserId final Long userId,
            @PathVariable("promiseId") final Long promiseId,
            @RequestBody final PreparationInfoDto preparationInfoDto
    ) {
        participantService.insertPreparationInfo(userId, promiseId, preparationInfoDto);
        return ResponseEntity.ok().build();
    }

    @IsMemberByPromiseId(promiseIdParamIndex = 0)
    @GetMapping("/v1/promises/{promiseId}/tardy")
    public ResponseEntity<LateComersDto> getLateComers(
            @PathVariable("promiseId") final Long promiseId
    ) {
        return ResponseEntity.ok().body(participantService.getLateComers(promiseId));
    }
}
