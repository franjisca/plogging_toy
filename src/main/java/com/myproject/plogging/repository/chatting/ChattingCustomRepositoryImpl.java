package com.myproject.plogging.repository.chatting;

import com.myproject.plogging.domain.QChatting;
import com.myproject.plogging.domain.QMeeting;
import com.myproject.plogging.dto.chatting.ChattingInfoDto;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static com.myproject.plogging.domain.QChatting.chatting;
import static com.myproject.plogging.domain.QMeeting.meeting;


@RequiredArgsConstructor
public class ChattingCustomRepositoryImpl implements ChattingCustomRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ChattingInfoDto> myMeetingList(Long id) {
        List<Tuple> data = queryFactory.select(
                        chatting.id, chatting.meeting.id
                        , meeting.maxCount, meeting.four
                        , meeting.three, meeting.two
                        , meeting.user.id.as("userNo")
                        , meeting.title
                        , meeting.period
                ).from(chatting).join(meeting).on(meeting.id.eq(chatting.meeting.id))
                .where(
                        meeting.user.id.eq(id)
                                .or(meeting.two.eq(id))
                                .or(meeting.three.eq(id))
                                .or(meeting.four.eq(id))
                )
                .fetch();

        System.out.println("meeting list: " + data);
        return data.stream().map(
                d -> ChattingInfoDto.builder()
                        .chatting_id(d.get(chatting.id))
                        .meeting_id(d.get(chatting.meeting.id))
                        .userNo(d.get(meeting.user.id.as("userNo")))
                        .two(d.get(meeting.two))
                        .three(d.get(meeting.three))
                        .four(d.get(meeting.four))
                        .title(d.get(meeting.title))
                        .max_count(d.get(meeting.maxCount))
                        .period(d.get(meeting.period))
                        .build()
        ).toList();
    }
}
