package com.myproject.plogging.repository.beforelist;

import com.myproject.plogging.domain.QBeforeList;
import com.myproject.plogging.domain.QMeeting;
import com.myproject.plogging.domain.QUser;
import com.myproject.plogging.dto.beforelist.BeforeListDto;
import com.myproject.plogging.dto.meeting.MeetingInfoDto;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.myproject.plogging.domain.QBeforeList.beforeList;
import static com.myproject.plogging.domain.QMeeting.meeting;
import static com.myproject.plogging.domain.QUser.user;

@RequiredArgsConstructor
public class BeforeListCustomRepositoryImpl implements BeforeListCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<BeforeListDto> myBeforeList(Long id) {

        List<Tuple> data = jpaQueryFactory.select(
                        beforeList.id, meeting.period, meeting.title
                ).from(beforeList).join(meeting).on(meeting.id.eq(beforeList.meeting.id))
                .where(beforeList.user.id.eq(id))
                .fetch();

        return data.stream().map(
                d -> BeforeListDto.builder()
                        .id(d.get(beforeList.id))
                        .period(d.get(meeting.period))
                        .title(d.get(meeting.title))
                        .build()).toList();
    }
}
