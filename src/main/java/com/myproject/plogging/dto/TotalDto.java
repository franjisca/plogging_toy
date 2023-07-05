package com.myproject.plogging.dto;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TotalDto {

    private Long peopleCount;
    public TotalDto(Long peopleCount) {
        this.peopleCount = peopleCount;
    }
}
