package com.myproject.plogging.repository.meeting;

import com.myproject.plogging.domain.Meeting;
import com.myproject.plogging.dto.meeting.MeetingInfoDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static com.myproject.plogging.domain.QMeeting.meeting;

@RequiredArgsConstructor
public class MeetingCustomRepositoryImpl implements MeetingCustomRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Meeting> meetingList() {

        return queryFactory
                .selectFrom(meeting)
                .where(meeting.user.id.isNotNull())
                .fetch();

    }

    @Override
    public boolean alreadyEnjoyed(Long userNo, Long meetingNo) {
        /*select * from meetings where meeting_id = 1 and two = 10 or three = 10 or four = 10;*/

        List<Meeting> list = queryFactory.selectFrom(meeting).where(meeting.id.eq(meetingNo).and(
                meeting.two.eq(userNo).or(meeting.three.eq(userNo).or(meeting.four.eq(userNo)))
        )).fetch();

        return list.size() > 0 ? true : false;
    }


}
