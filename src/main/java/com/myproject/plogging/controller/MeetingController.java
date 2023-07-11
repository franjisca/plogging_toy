package com.myproject.plogging.controller;


import com.myproject.plogging.domain.Meeting;
import com.myproject.plogging.dto.meeting.MeetingCreateDto;
import com.myproject.plogging.dto.meeting.MeetingInfoDto;
import com.myproject.plogging.service.MeetingService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meeting")
@RequiredArgsConstructor
public class MeetingController {

    private final MeetingService meetingService;

    @GetMapping("/list")
    public List<MeetingInfoDto> list() {
        return meetingService.meetingList();
    }

    @PostMapping("/create")
    public void createMeeting(@RequestBody MeetingCreateDto meetingCreateDto) {
        meetingService.createMeeting(meetingCreateDto);

    }

    @GetMapping("/info/{meetingNo}")
    public MeetingInfoDto unitInfo(@PathVariable("meetingNo") Long meetingNo) {
        return meetingService.unitMeeting(meetingNo);
    }

    @GetMapping("/enjoy/{meetingNo}/{userId}")
    public void enjoy(@PathVariable("userId") String userId, @PathVariable("meetingNo") Long meetingNo) {

        meetingService.enjoyMeeting(meetingNo, userId);
    }


}
