package org.kkumulkkum.server.controller;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.annotation.UserId;
import org.kkumulkkum.server.dto.promise.response.PromiseDto;
import org.kkumulkkum.server.dto.promise.response.PromisesDto;
import org.kkumulkkum.server.service.promise.PromiseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PromiseController {

    private final PromiseService promiseService;

    @GetMapping("/meetings/{meetingId}/promises")
    public ResponseEntity<PromisesDto> getPromises(
            @UserId final Long userId,
            @PathVariable("meetingId") final Long meetingId,
            @RequestParam(required = false) Boolean done
    ) {
        return ResponseEntity.ok().body(promiseService.getPromises(userId, meetingId, done));
    }
}
