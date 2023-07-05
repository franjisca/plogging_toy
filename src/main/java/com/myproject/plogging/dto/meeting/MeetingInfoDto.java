package com.myproject.plogging.dto.meeting;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MeetingInfoDto {

    private Long id;

    private Long userId;

    private String title;

    private String location;

    private String period;

    private int maxCount;

    private String contents;

    private Long two;

    private Long three;

    private Long four;


    @Builder
    public MeetingInfoDto(Long id, Long userId,
                          String title, String location,
                          String period, int maxCount,
                          String contents, Long two,
                          Long three, Long four) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.location = location;
        this.period = period;
        this.maxCount = maxCount;
        this.contents = contents;
        this.two = two;
        this.three = three;
        this.four = four;
    }
}
