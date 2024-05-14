package com.myproject.plogging.dto.notice;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class NoticeDto {

    String creator;
    String title;

    String contents;

    LocalDateTime createDate;


    @Builder
    public NoticeDto(String creator, String title, String contents, LocalDateTime createDate) {
        this.creator = creator;
        this.title = title;
        this.contents = contents;
        this.createDate = createDate;
    }


}
