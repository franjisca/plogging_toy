package com.myproject.plogging.dto.meeting;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MeetingCreateDto {
    private String userId;
    private String title;
    private String location;
    private String period;
    private int maxCount;
    private String contents;


    @Builder
    public MeetingCreateDto(String userId, String title, String location,
                            String period, int maxCount, String contents) {
        this.userId = userId;
        this.title = title;
        this.location = location;
        this.period = period;
        this.maxCount = maxCount;
        this.contents = contents;
    }
}
