package com.myproject.plogging.repository.notice;

import com.myproject.plogging.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NoticeRepository extends JpaRepository<Notice, Long>, NoticeCustomRepository{
}
