package com.myproject.plogging.repository.notice;

import com.myproject.plogging.domain.Notice;
import com.myproject.plogging.domain.QNotice;
import com.myproject.plogging.dto.notice.NoticeDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.myproject.plogging.domain.QNotice.notice;


@RequiredArgsConstructor
public class NoticeCustomRepositoryImpl implements NoticeCustomRepository{


    private final JPAQueryFactory queryFactory;

    @Override
    public List<NoticeDto> getNoticeList() {

        List<Notice> dataList = queryFactory.selectFrom(notice).orderBy(notice.createDate.desc()).fetch();

        return  dataList
                .stream()
                .map(
                notice -> NoticeDto.builder()
                        .creator(notice.getUser().getUsername())
                        .title(notice.getTitle())
                        .contents(notice.getContents())
                        .createDate(notice.getCreateDate())
                        .build()).toList();
    }

    @Override
    public void writeNotice(String admin) {

    }
}
