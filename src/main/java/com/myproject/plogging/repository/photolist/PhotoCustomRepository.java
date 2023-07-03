package com.myproject.plogging.repository.photolist;


import com.querydsl.jpa.impl.JPAQuery;

public interface PhotoCustomRepository {

    public void addCount(Long photoNo);

    Long mainCount();
}
