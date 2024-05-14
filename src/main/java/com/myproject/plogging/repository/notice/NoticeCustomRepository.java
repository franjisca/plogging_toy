package com.myproject.plogging.repository.notice;

import com.myproject.plogging.domain.Notice;
import com.myproject.plogging.domain.User;
import com.myproject.plogging.dto.notice.NoticeDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeCustomRepository {

    List<NoticeDto> getNoticeList();

    void writeNotice(String admin);
}
