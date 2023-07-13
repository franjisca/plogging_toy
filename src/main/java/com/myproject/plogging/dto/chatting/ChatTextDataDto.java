package com.myproject.plogging.dto.chatting;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ChatTextDataDto {

    private Long chattingId;

    private LocalDateTime createDate;

    private Long textId;

    private String chattingText;

    private String userId;


    @Builder
    public ChatTextDataDto(Long chattingId, LocalDateTime createDate, Long textId, String chattingText, String userId) {
        this.chattingId = chattingId;
        this.createDate = createDate;
        this.textId = textId;
        this.chattingText = chattingText;
        this.userId = userId;
    }
}
