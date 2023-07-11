package com.myproject.plogging.controller;


import com.myproject.plogging.domain.BeforeList;
import com.myproject.plogging.dto.beforelist.BeforeListDto;
import com.myproject.plogging.dto.chatting.ChattingInfoDto;
import com.myproject.plogging.dto.meeting.MeetingInfoDto;
import com.myproject.plogging.dto.user.UserDataDto;
import com.myproject.plogging.dto.user.UserInfoChangeDto;
import com.myproject.plogging.service.ChattingService;
import com.myproject.plogging.service.MeetingService;
import com.myproject.plogging.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/my-page")
public class MyPageController {

    private final UserService userService;

    private final ChattingService chattingService;

    private final MeetingService meetingService;


    @GetMapping("/my-info/{userId}")
    public UserDataDto getMyInfo(@PathVariable("userId") String userId){
        return userService.getMyInfo(userId);
    }

    @GetMapping("/count/{userId}")
    public Long getMyPloggingCount(@PathVariable("userId") String useId){
        return null;
    }

    @GetMapping("/meeting-list/{userId}")
    public List<ChattingInfoDto> myChattingList(@PathVariable("userId") String userId){
        return chattingService.myChattingList(userId);
    }

    @GetMapping("/chatting/{chattingId}")
    public void isValidChatting(@PathVariable("chattingId")  Long meetingId) {
        chattingService.isValid(meetingId);
    }

    @DeleteMapping("/out/{meetingId}/{userId}")
    public void leaveMeeting(@PathVariable("meetingId") Long meetingId,@PathVariable("userId") String userId) {
        meetingService.leaveMeeting(meetingId,userId);
    }

    @GetMapping("/before-list/{userId}")
    public List<BeforeListDto> myBeforeList(@PathVariable("userId") String userId) {
        return meetingService.myBeforeList(userId);
    }

    @PatchMapping ("/info-change/{userId}")
    public UserInfoChangeDto infoChange(@PathVariable("userId") String userId, UserInfoChangeDto dto) {
        return userService.infoChange(userId, dto);
    }


}
