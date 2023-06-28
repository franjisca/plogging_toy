package com.myproject.plogging.dto.user;

import lombok.*;

@Data
public class UserInfoChangeDto {

    private String nickname;

    private String password;

    private String email;

    private String phone;

    private String address;

}
