package org.kkumulkkum.server.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.annotation.IsMemberByMeetingId;
import org.kkumulkkum.server.annotation.UserId;
import org.kkumulkkum.server.dto.meeting.request.MeetingCreateDto;
import org.kkumulkkum.server.dto.meeting.request.MeetingRegisterDto;
import org.kkumulkkum.server.dto.meeting.response.CreatedMeetingDto;
import org.kkumulkkum.server.dto.meeting.response.MeetingDto;
import org.kkumulkkum.server.dto.meeting.response.MeetingIdDto;
import org.kkumulkkum.server.dto.meeting.response.MeetingsDto;
import org.kkumulkkum.server.dto.member.response.MembersDto;
import org.kkumulkkum.server.service.meeting.MeetingService;
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
            @PathVariable final Long meetingId
    ) {
        return ResponseEntity.ok(meetingService.getMembers(meetingId));
    }

}
