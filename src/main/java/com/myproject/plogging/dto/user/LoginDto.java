package com.myproject.plogging.dto.user;


import lombok.*;

@Data
@NoArgsConstructor
public class LoginDto {

    private String userId;
    private String password;

    @Builder
    public LoginDto(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }


}
