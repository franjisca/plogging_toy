package com.myproject.plogging.repository.photolist;


import com.myproject.plogging.domain.PhotoList;

import java.util.List;

public interface PhotoCustomRepository {

    public void addCount(Long photoNo);

    Long mainCount();

    List<PhotoList> photoList();
}
