package com.myproject.plogging.controller;


import com.myproject.plogging.dto.chatting.ChatTextDataDto;
import com.myproject.plogging.dto.chatting.TextDto;
import com.myproject.plogging.service.ChattingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/chatting")

public class ChattingController {

    private final ChattingService chattingService;

    @PostMapping("/send/{chattingId}/{userId}")
    public void sendMessage(
            @PathVariable("chattingId") Long chattingId,
            @PathVariable("userId") String userId,
            @RequestBody TextDto textDto){
        log.info("textDto: {}", textDto.getMessages());
        chattingService.sendMessages(chattingId, userId, textDto);
    }

    @GetMapping("/chatting-data/{chattingId}")
    public List<ChatTextDataDto> getChattingData(@PathVariable("chattingId") Long chattingId){
        return chattingService.getChattingData(chattingId);
    }

}
