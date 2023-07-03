package com.myproject.plogging.controller;


import com.myproject.plogging.domain.Meeting;
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
    public List<Meeting> list() {
        return meetingService.meetingList();
    }

    @PostMapping("/create/{userNo}")
    public Meeting createMeeting(@PathVariable("userNo") Long userNo, @RequestBody Meeting meeting) {

        return meetingService.createMeeting(userNo, meeting);
    }

    @GetMapping("/info/{meetingNo}")
    public Meeting unitInfo(@PathVariable("meetingNo") Long meetingNo) {
        return meetingService.unitMeeting(meetingNo);
    }

    @GetMapping("/enjoy/{meetingNo}/{userId}")
    public void enjoy(@PathVariable("userId") String userId, @PathVariable("meetingNo") Long meetingNo) {
        meetingService.enjoyMeeting(meetingNo, userId);
    }


}
