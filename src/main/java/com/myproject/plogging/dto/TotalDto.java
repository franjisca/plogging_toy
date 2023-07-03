package com.myproject.plogging.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TotalDto {

    private Long peopleCount;
    public TotalDto(Long peopleCount) {
        this.peopleCount = peopleCount;
    }
}
