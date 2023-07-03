package com.myproject.plogging.repository.photolist;

import com.myproject.plogging.domain.PhotoList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoListRepository extends JpaRepository<PhotoList, Long>, PhotoCustomRepository {
}
