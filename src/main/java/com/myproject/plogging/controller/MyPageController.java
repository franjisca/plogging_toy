package com.myproject.plogging.controller;


import com.myproject.plogging.dto.beforelist.BeforeListDto;
import com.myproject.plogging.dto.chatting.ChattingInfoDto;
import com.myproject.plogging.dto.marker.MarkerListDto;
import com.myproject.plogging.dto.photo.PhotoDto;
import com.myproject.plogging.dto.user.UserDataDto;
import com.myproject.plogging.dto.user.UserInfoChangeDto;
import com.myproject.plogging.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/my-page")
@Slf4j
public class MyPageController {

    private final UserService userService;

    private final ChattingService chattingService;

    private final MeetingService meetingService;

    private final PhotoService photoService;

    private final MarkerService markerService;


    @GetMapping("/my-info/{userId}")
    public UserDataDto getMyInfo(@PathVariable("userId") String userId){
        return userService.getMyInfo(userId);
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
    public UserInfoChangeDto infoChange(@PathVariable("userId") String userId, @RequestBody UserInfoChangeDto dto) {
        return userService.infoChange(userId, dto);
    }

    @GetMapping("/my-photos/{userId}")
    public List<PhotoDto> myPhotoList(@PathVariable("userId") String userId) {
        return photoService.userPhotoList(userId);
    }

    @GetMapping("/count/{userId}")
    public Long myPloggingCount(@PathVariable("userId") String userId){
        return photoService.myPloggingCount(userId);
    }


    @GetMapping("/my-marker/{userId}")
    public List<MarkerListDto> myMarkerList(@PathVariable("userId") String userId) {
        return markerService.getMyMarkerList(userId);
    }
}
