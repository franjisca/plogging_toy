package com.myproject.plogging.service;


import com.myproject.plogging.domain.Meeting;
import com.myproject.plogging.domain.User;
import com.myproject.plogging.repository.meeting.MeetingRepository;
import com.myproject.plogging.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sound.midi.Soundbank;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MeetingService {

    private final MeetingRepository meetingRepository;

    private final UserRepository userRepository;

    public List<Meeting> meetingList() {
        return meetingRepository.meetingList();
    }

    @Transactional
    public Meeting createMeeting(Long userNo, Meeting meeting) {

        if(userNo == null) throw new IllegalArgumentException();

        User user = userRepository.findById(userNo).orElseThrow(IllegalArgumentException::new);

        meeting.setUser(user);

        return meetingRepository.save(meeting);
    }

    public Meeting unitMeeting(Long meetingNo) {

        return meetingRepository.findById(meetingNo).orElseThrow(IllegalArgumentException::new);

    }

    @Transactional
    public void enjoyMeeting(Long meetingNo, String userId) {

        User user = userRepository.findByUserStrId(userId);

        Meeting meeting = meetingRepository.findById(meetingNo).orElseThrow(IllegalArgumentException::new);

        //더티체킹
        meeting.enjoyMeeting(user.getId());

    }
}
