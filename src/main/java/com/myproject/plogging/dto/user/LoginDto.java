package com.myproject.plogging.dto.user;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginDto {

    private String userId;
    private String password;

}
