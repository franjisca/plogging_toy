package com.myproject.plogging.repository.chatting;

import com.myproject.plogging.dto.chatting.ChattingInfoDto;

import java.util.List;

public interface ChattingCustomRepository {

    List<ChattingInfoDto> myMeetingList(Long id);
}
