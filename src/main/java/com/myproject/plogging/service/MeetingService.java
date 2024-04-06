package com.myproject.plogging.service;


import com.myproject.plogging.domain.*;
import com.myproject.plogging.dto.beforelist.BeforeListDto;
import com.myproject.plogging.dto.meeting.MeetingCreateDto;
import com.myproject.plogging.dto.meeting.MeetingInfoDto;
import com.myproject.plogging.exception.UserNotFoundException;
import com.myproject.plogging.repository.beforelist.BeforeListRepository;
import com.myproject.plogging.repository.chatting.ChattingRepository;
import com.myproject.plogging.repository.marker.MarkerRepository;
import com.myproject.plogging.repository.meeting.MeetingRepository;
import com.myproject.plogging.repository.user.UserRepository;
import com.myproject.plogging.util.FindGeoCode;
import com.myproject.plogging.util.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MeetingService {

    private final MeetingRepository meetingRepository;

    private final UserRepository userRepository;

    private final ChattingRepository chattingRepository;

    private final BeforeListRepository beforeListRepository;

    private final MailService mailService;

    private final FindGeoCode findGeoCode;

    private final MarkerRepository markerRepository;


    public List<MeetingInfoDto> meetingList() {


        List<Meeting> meetings = meetingRepository.meetingList();

        return meetings.stream().map(meeting -> new MeetingInfoDto().builder()
                .id(meeting.getId())
                .userId(meeting.getUser().getId())
                .title(meeting.getTitle())
                .location(meeting.getLocation())
                .period(meeting.getPeriod())
                .maxCount(meeting.getMaxCount())
                .contents(meeting.getContents())
                .two(meeting.getTwo())
                .three(meeting.getThree())
                .four(meeting.getFour())
                .build()
        ).collect(Collectors.toList());

    }

    @Transactional
    public Meeting createMeeting(MeetingCreateDto dto) {

        if(dto.getUserId() == null) throw new IllegalArgumentException();

        User user = userRepository.findByUserStrId(dto.getUserId());

        if(user == null) throw new UserNotFoundException("유저 정보가 존재하지 않습니다");

        Meeting meeting = Meeting.builder()
                .user(user)
                .title(dto.getTitle())
                .location(dto.getLocation())
                .period(dto.getPeriod())
                .contents(dto.getContents())
                .maxCount(dto.getMaxCount())
                .build();


        Meeting saveMeeting = meetingRepository.save(meeting);
        chattingRepository.save( new Chatting(saveMeeting));

        log.info("meeting number order: {}", saveMeeting.getId());

        mailService.whenEnjoyMeeting(user, saveMeeting, true);

        //marker service
        Map<String, String> geoData = findGeoCode.getGeoData(dto.getLocation());

        Marker marker = Marker.builder().user(user).lotd(geoData.get("x")).latd(geoData.get("y")).build();
        markerRepository.save(marker);

        return saveMeeting;
    }

    public MeetingInfoDto unitMeeting(Long meetingNo) {

        Meeting meeting = meetingRepository.findById(meetingNo).orElseThrow(IllegalArgumentException::new);

        return new MeetingInfoDto().builder()
                .id(meeting.getId())
                .userId(meeting.getUser().getId())
                .title(meeting.getTitle())
                .location(meeting.getLocation())
                .period(meeting.getPeriod())
                .maxCount(meeting.getMaxCount())
                .contents(meeting.getContents())
                .two(meeting.getTwo())
                .three(meeting.getThree())
                .four(meeting.getFour())
                .build();
    }

    @Transactional
    public void enjoyMeeting(Long meetingNo, String userId) {

        User user = userRepository.findByUserStrId(userId);

        boolean continueProcess = meetingRepository.alreadyEnjoyed(user.getId(), meetingNo);




        if(!continueProcess){
            Meeting meeting = meetingRepository.findById(meetingNo).orElseThrow(IllegalArgumentException::new);
            //더티체킹
            meeting.enjoyMeeting(user.getId());
            mailService.whenEnjoyMeeting(user, meeting, false);
        } else {
            throw new IllegalArgumentException("이미 참여중인 모임입니다.");
        }
    }

    @Transactional
    public void leaveMeeting(Long meetingId,String userId) {
        User user = userRepository.findByUserStrId(userId);

        if(user == null) {
            throw new UserNotFoundException("유저 정보를 찾을 수 없습니다.");
        }

        Meeting meeting = meetingRepository.findById(meetingId).orElseThrow(IllegalArgumentException::new);

        meeting.leaveMeeting(user.getId());

        beforeListRepository.save(BeforeList
                .builder()
                .meeting(meeting)
                .user(user)
                .build());
    }

    public List<BeforeListDto> myBeforeList(String userId) {
        User user = userRepository.findByUserStrId(userId);

        if(user == null) {
            throw new UserNotFoundException("사용자를 찾을 수 없습니다.");
        }

        return beforeListRepository.myBeforeList(user.getId());
    }
}
