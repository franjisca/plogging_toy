package com.myproject.plogging.dto.beforelist;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BeforeListDto {

    private Long id;

    private String title;

    private String period;




    @Builder
    public BeforeListDto(Long id, String title, String period) {
        this.id = id;
        this.title = title;
        this.period = period;
    }

}
