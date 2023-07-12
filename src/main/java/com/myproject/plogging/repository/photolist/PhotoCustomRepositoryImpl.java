package com.myproject.plogging.repository.photolist;


import com.myproject.plogging.domain.PhotoList;
import com.myproject.plogging.dto.photo.PhotoDto;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.dsl.DateTemplate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.myproject.plogging.domain.QPhotoList.photoList;
import static java.nio.file.attribute.FileTime.from;

@RequiredArgsConstructor
public class PhotoCustomRepositoryImpl implements PhotoCustomRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public void addCount(Long photoNo) {
        queryFactory
                .update(photoList)
                .set(photoList.likes ,photoList.likes.add(1)).where(photoList.id.eq(photoNo))
                .execute();
    }

    public Long mainCount() {
        DateTemplate<String> findData = Expressions.dateTemplate(
                String.class,
                "DATE_FORMAT({0}, {1})",
                photoList.uploadDate,
                ConstantImpl.create("%Y-%m-%d"));

        return queryFactory
                .selectFrom(photoList)
                .where(
                      findData.eq(Expressions.currentDate().stringValue())
                        )
                .fetchCount();
    }

    @Override
    public Long myPloggingCount(Long id) {
        return queryFactory.selectFrom(photoList).where(photoList.user.id.eq(id)).fetchCount();
    }

    @Override
    public List<PhotoList> photoList() {
        return queryFactory
                .selectFrom(photoList)
                .orderBy(photoList.uploadDate.desc())
                .fetch();
    }

    @Override
    public List<PhotoDto> userPhotoList(Long userNo) {
        List<PhotoList> data = queryFactory
                .selectFrom(photoList)
                .orderBy(photoList.uploadDate.desc())
                .where(photoList.user.id.eq(userNo))
                .fetch();

        return data.stream().map(
                photo -> new PhotoDto(
                        photo.getId(), photo.getUser().getUserId(),
                        photo.getImage(),
                        photo.getStoredFilename(),
                        photo.getLikes(),
                        photo.getUploadDate()
                )
        ).toList();
    }
}
