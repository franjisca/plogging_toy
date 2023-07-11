package com.myproject.plogging.dto.chatting;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChattingInfoDto {


    private Long chatting_id;

    private Long meeting_id;

    private int max_count;

    private Long four;

    private Long three;

    private Long two;

    private Long userNo;

    private String title;

    private String period;

    @Builder
    public ChattingInfoDto(Long chatting_id, Long meeting_id,
                           int max_count, Long four,
                           Long three, Long two,
                           Long userNo, String title,
                           String period
                           ) {
        this.chatting_id = chatting_id;
        this.meeting_id = meeting_id;
        this.max_count = max_count;
        this.four = four;
        this.three = three;
        this.two = two;
        this.userNo = userNo;
        this.title = title;
        this.period = period;
    }

}
