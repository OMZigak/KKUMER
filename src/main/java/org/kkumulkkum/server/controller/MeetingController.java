package org.kkumulkkum.server.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.annotation.UserId;
import org.kkumulkkum.server.dto.meeting.request.MeetingCreateDto;
import org.kkumulkkum.server.dto.meeting.request.MeetingRegisterDto;
import org.kkumulkkum.server.dto.meeting.response.CreatedMeetingDto;
import org.kkumulkkum.server.dto.meeting.response.MeetingDto;
import org.kkumulkkum.server.dto.meeting.response.MeetingsDto;
import org.kkumulkkum.server.dto.member.response.MembersDto;
import org.kkumulkkum.server.service.meeting.MeetingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/meetings/register")
    public ResponseEntity<Void> registerMeeting(
            @UserId Long userId,
            @Valid @RequestBody MeetingRegisterDto meetingRegisterDto
    ) {
        meetingService.registerMeeting(userId, meetingRegisterDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/meetings")
    public ResponseEntity<MeetingsDto> getMeetings(
            @UserId Long userId
    ) {
        return ResponseEntity.ok(meetingService.getMeetings(userId));
    }

    @GetMapping("/meetings/{meetingId}")
    public ResponseEntity<MeetingDto> getMeeting(
            @UserId Long userId,
            @PathVariable Long meetingId
    ) {
        return ResponseEntity.ok(meetingService.getMeeting(userId, meetingId));
    }

    @GetMapping("/meetings/{meetingId}/members")
    public ResponseEntity<MembersDto> getMembers(
            @UserId Long userId,
            @PathVariable Long meetingId
    ) {
        return ResponseEntity.ok(meetingService.getMembers(userId, meetingId));
    }

}
