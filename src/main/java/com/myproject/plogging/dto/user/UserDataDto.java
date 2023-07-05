package com.myproject.plogging.dto.user;


import lombok.*;

// 나중에 db에서 받아온 데이터 바로 user에 담지 않고 해당 dto로 담을 것
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDataDto {

    private Long id;

    private String userId;
    private String username;

    private String nickname;

    private String password;

    private String email;

    private String phone;

    private String address;

}
