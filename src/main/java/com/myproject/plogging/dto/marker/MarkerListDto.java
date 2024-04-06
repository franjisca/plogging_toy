package com.myproject.plogging.dto.marker;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MarkerListDto {

    String lotd;

    String latd;

    @Builder
    public MarkerListDto(String lotd, String latd) {
        this.lotd = lotd;
        this.latd = latd;
    }
}
