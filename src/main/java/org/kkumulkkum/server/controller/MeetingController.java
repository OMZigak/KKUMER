package org.kkumulkkum.server.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.annotation.IsMemberByMeetingId;
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
            @UserId final Long userId,
            @Valid @RequestBody final MeetingCreateDto meetingCreateDto
    ) {
        CreatedMeetingDto createdMeetingDto = meetingService.createMeeting(userId, meetingCreateDto);
        return ResponseEntity
                .created(URI.create(createdMeetingDto.id().toString()))
                .body(createdMeetingDto);
    }

    @PostMapping("/meetings/register")
    public ResponseEntity<Void> registerMeeting(
            @UserId final Long userId,
            @Valid @RequestBody final MeetingRegisterDto meetingRegisterDto
    ) {
        meetingService.registerMeeting(userId, meetingRegisterDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/meetings")
    public ResponseEntity<MeetingsDto> getMeetings(
            @UserId final Long userId
    ) {
        return ResponseEntity.ok(meetingService.getMeetings(userId));
    }

    @IsMemberByMeetingId(meetingIdParamIndex = 0)
    @GetMapping("/meetings/{meetingId}")
    public ResponseEntity<MeetingDto> getMeeting(
            @PathVariable final Long meetingId
    ) {
        return ResponseEntity.ok(meetingService.getMeeting(meetingId));
    }

    @IsMemberByMeetingId(meetingIdParamIndex = 0)
    @GetMapping("/meetings/{meetingId}/members")
    public ResponseEntity<MembersDto> getMembers(
            @PathVariable final Long meetingId
    ) {
        return ResponseEntity.ok(meetingService.getMembers(meetingId));
    }

}
