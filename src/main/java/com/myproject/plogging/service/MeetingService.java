package com.myproject.plogging.service;


import com.myproject.plogging.domain.Meeting;
import com.myproject.plogging.domain.User;
import com.myproject.plogging.dto.meeting.MeetingCreateDto;
import com.myproject.plogging.dto.meeting.MeetingInfoDto;
import com.myproject.plogging.exception.UserNotFoundException;
import com.myproject.plogging.repository.meeting.MeetingRepository;
import com.myproject.plogging.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sound.midi.Soundbank;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MeetingService {

    private final MeetingRepository meetingRepository;

    private final UserRepository userRepository;

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

        return meetingRepository.save(meeting);
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
                .build()
                ;
    }

    @Transactional
    public void enjoyMeeting(Long meetingNo, String userId) {

        User user = userRepository.findByUserStrId(userId);

        Meeting meeting = meetingRepository.findById(meetingNo).orElseThrow(IllegalArgumentException::new);

        //더티체킹
        meeting.enjoyMeeting(user.getId());

    }
}
