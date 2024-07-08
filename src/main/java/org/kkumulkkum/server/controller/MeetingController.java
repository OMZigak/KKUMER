package org.kkumulkkum.server.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.annotation.UserId;
import org.kkumulkkum.server.dto.meeting.request.MeetingCreateDto;
import org.kkumulkkum.server.dto.meeting.response.CreatedMeetingDto;
import org.kkumulkkum.server.service.meeting.MeetingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MeetingController {

    private final MeetingService meetingService;

    @PostMapping("/meetings")
    public ResponseEntity<CreatedMeetingDto> createMeeting(
            @UserId Long userId,
            @Valid @RequestBody MeetingCreateDto meetingCreateDto
    ) {
        CreatedMeetingDto createdMeetingDto = meetingService.createMeeting(userId, meetingCreateDto);
        return ResponseEntity
                .created(URI.create(createdMeetingDto.id().toString()))
                .body(createdMeetingDto);
    }

}
