package com.myproject.plogging.repository.marker;

import com.myproject.plogging.dto.marker.MarkerListDto;

import java.util.List;

public interface MarkerCustomRepository {


    List<MarkerListDto> userMarkerList(Long id);
}
