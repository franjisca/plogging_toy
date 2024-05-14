package com.myproject.plogging.controller;


import com.myproject.plogging.dto.notice.NoticeDto;
import com.myproject.plogging.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;


    @GetMapping("/list")
    public List<NoticeDto> getNoticeList(){
        return noticeService.getNoticeList();
    }
}
