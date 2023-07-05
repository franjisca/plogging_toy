package com.myproject.plogging.dto.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoChangeDto {

    private String nickname;

    private String password;

    private String email;

    private String phone;

    private String address;

}
