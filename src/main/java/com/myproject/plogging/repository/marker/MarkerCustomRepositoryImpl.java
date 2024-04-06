package com.myproject.plogging.repository.marker;


import com.myproject.plogging.dto.marker.MarkerListDto;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.myproject.plogging.domain.QMarker.marker;

@RequiredArgsConstructor
public class MarkerCustomRepositoryImpl implements MarkerCustomRepository{
    private  final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<MarkerListDto> userMarkerList(Long id) {
        List<Tuple> data = jpaQueryFactory
                .select(marker.lotd, marker.latd)
                .from(marker)
                .where(marker.user.id.eq(id))
                .fetch();

        return data.stream().map(
                d -> MarkerListDto.builder()
                        .lotd(d.get(marker.lotd))
                        .latd(d.get(marker.latd))
                        .build())
                .toList();
    }
}
