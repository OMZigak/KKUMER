package org.kkumulkkum.server.api.meeting.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.annotation.IsMemberByMeetingId;
import org.kkumulkkum.server.annotation.UserId;
import org.kkumulkkum.server.api.meeting.dto.request.MeetingCreateDto;
import org.kkumulkkum.server.api.meeting.dto.request.MeetingRegisterDto;
import org.kkumulkkum.server.api.meeting.dto.response.CreatedMeetingDto;
import org.kkumulkkum.server.api.meeting.dto.response.MeetingDto;
import org.kkumulkkum.server.api.meeting.dto.response.MeetingIdDto;
import org.kkumulkkum.server.api.meeting.dto.response.MeetingsDto;
import org.kkumulkkum.server.api.meeting.dto.response.MembersDto;
import org.kkumulkkum.server.api.meeting.service.MeetingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class MeetingController {

    private final MeetingService meetingService;

    @PostMapping("/v1/meetings")
    public ResponseEntity<CreatedMeetingDto> createMeeting(
            @UserId final Long userId,
            @Valid @RequestBody final MeetingCreateDto meetingCreateDto
    ) {
        CreatedMeetingDto createdMeetingDto = meetingService.createMeeting(userId, meetingCreateDto);
        return ResponseEntity
                .created(URI.create(createdMeetingDto.meetingId().toString()))
                .body(createdMeetingDto);
    }

    @PostMapping("/v1/meetings/register")
    public ResponseEntity<MeetingIdDto> registerMeeting(
            @UserId final Long userId,
            @Valid @RequestBody final MeetingRegisterDto meetingRegisterDto
    ) {
        Long meetingId = meetingService.registerMeeting(userId, meetingRegisterDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new MeetingIdDto(meetingId));
    }

    @GetMapping("/v1/meetings")
    public ResponseEntity<MeetingsDto> getMeetings(
            @UserId final Long userId
    ) {
        return ResponseEntity.ok(meetingService.getMeetings(userId));
    }

    @IsMemberByMeetingId(meetingIdParamIndex = 0)
    @GetMapping("/v1/meetings/{meetingId}")
    public ResponseEntity<MeetingDto> getMeeting(
            @PathVariable final Long meetingId
    ) {
        return ResponseEntity.ok(meetingService.getMeeting(meetingId));
    }

    @IsMemberByMeetingId(meetingIdParamIndex = 0)
    @GetMapping("/v1/meetings/{meetingId}/members")
    public ResponseEntity<MembersDto> getMembers(
            @PathVariable final Long meetingId,
            @RequestParam(name = "exclude", required = false) final String exclude,
            @UserId final Long userId
    ) {
        return ResponseEntity.ok(meetingService.getMembers(meetingId, exclude, userId));
    }

    @IsMemberByMeetingId(meetingIdParamIndex = 0)
    @PatchMapping("/v1/meetings/{meetingId}")
    public ResponseEntity<Void> updateMeeting(
            @PathVariable final Long meetingId,
            @Valid @RequestBody final MeetingCreateDto meetingCreateDto
    ) {
        meetingService.updateMeeting(meetingId, meetingCreateDto);
        return ResponseEntity.ok().build();
    }

    @IsMemberByMeetingId(meetingIdParamIndex = 1)
    @DeleteMapping("/v1/meetings/{meetingId}")
    public ResponseEntity<Void> leaveMeeting(
            @UserId final Long userId,
            @PathVariable final Long meetingId
    ) {
        meetingService.leaveMeeting(userId, meetingId);
        return ResponseEntity.ok().build();
    }

}
