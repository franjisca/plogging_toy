package com.myproject.plogging.dto.user;


import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoginDto {

    private String userId;
    private String password;

    public LoginDto(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

}
