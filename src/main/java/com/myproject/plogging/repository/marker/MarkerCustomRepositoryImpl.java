package com.myproject.plogging.repository.marker;


import com.myproject.plogging.dto.marker.MarkerListDto;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class MarkerCustomRepositoryImpl implements MarkerCustomRepository{

    @Override
    public List<MarkerListDto> userMarkerList(Long id) {
        return null;
    }
}
