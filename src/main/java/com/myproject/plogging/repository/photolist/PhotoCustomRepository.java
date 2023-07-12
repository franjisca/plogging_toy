package com.myproject.plogging.repository.photolist;


import com.myproject.plogging.domain.PhotoList;
import com.myproject.plogging.dto.photo.PhotoDto;

import java.util.List;

public interface PhotoCustomRepository {

    void addCount(Long photoNo);

    Long mainCount();

    List<PhotoList> photoList();

    List<PhotoDto> userPhotoList(Long userNo);

    Long myPloggingCount(Long id);
}
