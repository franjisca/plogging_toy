package com.myproject.plogging.repository.meeting;

import com.myproject.plogging.domain.Meeting;
import com.myproject.plogging.domain.QMeeting;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class MeetingCustomRepositoryImpl implements MeetingCustomRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Meeting> meetingList() {

        return queryFactory.selectFrom(QMeeting.meeting).fetch();

    }
}
