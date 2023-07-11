package com.myproject.plogging.dto.user;


import lombok.*;

// 나중에 db에서 받아온 데이터 바로 user에 담지 않고 해당 dto로 담을 것
@Getter
@Setter
@NoArgsConstructor
public class UserDataDto {

    private String userId;
    private String username;

    private String nickname;

    private String password;

    private String email;

    private String phone;

    private String address;


    @Builder
    public UserDataDto(String userId, String username,
                       String nickname, String password,
                       String email, String phone,
                       String address) {
        this.userId = userId;
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }
}
