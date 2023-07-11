package com.myproject.plogging.repository.beforelist;

import com.myproject.plogging.dto.beforelist.BeforeListDto;

import java.util.List;

public interface BeforeListCustomRepository {

    List<BeforeListDto> myBeforeList(Long id);
}
