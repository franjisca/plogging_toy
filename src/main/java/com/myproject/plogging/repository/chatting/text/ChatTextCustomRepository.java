package com.myproject.plogging.repository.chatting.text;

import com.myproject.plogging.dto.chatting.ChatTextDataDto;

import java.util.List;

public interface ChatTextCustomRepository {

    List<ChatTextDataDto> chattingData(Long chattingId);
}
