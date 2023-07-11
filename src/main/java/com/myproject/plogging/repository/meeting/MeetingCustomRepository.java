package com.myproject.plogging.repository.meeting;

import com.myproject.plogging.domain.Meeting;
import com.myproject.plogging.dto.meeting.MeetingInfoDto;

import java.util.List;

public interface MeetingCustomRepository {

    public List<Meeting> meetingList();

    public boolean alreadyEnjoyed(Long userNo, Long meetingNo);


}
