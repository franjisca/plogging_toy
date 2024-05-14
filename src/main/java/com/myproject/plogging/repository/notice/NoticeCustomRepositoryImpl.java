package com.myproject.plogging.repository.notice;

import com.myproject.plogging.dto.notice.NoticeDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class NoticeCustomRepositoryImpl implements NoticeCustomRepository{


    private final JPAQueryFactory queryFactory;

    @Override
    public List<NoticeDto> getNoticeList() {
        return null;
    }

    @Override
    public void writeNotice(String admin) {

    }
}
