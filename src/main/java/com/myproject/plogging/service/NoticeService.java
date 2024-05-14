package com.myproject.plogging.service;


import com.myproject.plogging.dto.notice.NoticeDto;
import com.myproject.plogging.repository.notice.NoticeCustomRepositoryImpl;
import com.myproject.plogging.repository.notice.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public List<NoticeDto> getNoticeList() {

        return noticeRepository.getNoticeList();
    }


}
