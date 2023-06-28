package com.myproject.plogging.dto.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class UserInfoChangeDto {
    
    private String nickname;

    private String password;

    private String email;

    private String phone;

    private String address;

}
