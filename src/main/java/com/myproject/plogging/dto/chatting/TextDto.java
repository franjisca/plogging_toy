package com.myproject.plogging.dto.chatting;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TextDto {

    private String messages;

    public TextDto(String messages) {
        this.messages = messages;
    }
}
