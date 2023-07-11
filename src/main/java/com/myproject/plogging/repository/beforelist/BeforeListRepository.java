package com.myproject.plogging.repository.beforelist;

import com.myproject.plogging.domain.BeforeList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeforeListRepository extends JpaRepository<BeforeList, Long>, BeforeListCustomRepository {
}
