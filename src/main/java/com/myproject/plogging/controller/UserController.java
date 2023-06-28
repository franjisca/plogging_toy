package com.myproject.plogging.controller;


import com.myproject.plogging.config.CustomBcryptEncoder;
import com.myproject.plogging.domain.User;
import com.myproject.plogging.dto.user.LoginDto;
import com.myproject.plogging.dto.user.UserInfoChangeDto;
import com.myproject.plogging.exception.UserNotFoundException;
import com.myproject.plogging.repository.UserRepository;
import com.nimbusds.openid.connect.sdk.claims.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequiredArgsConstructor
@Transactional
public class UserController {

    private final UserRepository userRepository;

    private final CustomBcryptEncoder bcryptEncoder;

    @PostMapping("/signup")
    public User signup(@RequestBody User user) {

        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        User saveUser = userRepository.save(user);

        return user;
    }

    @GetMapping("/loginpage")
    public String loginPage() {
        return "loginPage";
    }


    @PostMapping("/login")
    public User login(@RequestBody LoginDto loginDto) {

        User user = userRepository.findByUserIdAndPwd(loginDto);
        System.out.println("유저 정보" + user);
        if(user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", loginDto.getUserId()));
        }
        return user;
    }


    // 나중에
    @PatchMapping ("/info-change/{userId}")
    public UserInfoChangeDto infoChange(@PathVariable("userId") String userId, @RequestBody UserInfoChangeDto dto){

        User user = userRepository.findByUserid(userId);

        if(user == null) {
            throw new IllegalArgumentException("변경할 수 없음");
        }

        // 더티 체킹으로
        if(dto.getNickname() != null) {
            user.updateNickname(dto.getNickname());
        }

        if(dto.getPassword() != null) {
            user.updatePassword(dto.getNickname());
        }

        if(dto.getEmail() != null) {
            user.updateEmail(dto.getEmail());
        }

        if(dto.getPhone() != null) {
            user.updatePhone(dto.getPhone());
        }

        if(dto.getAddress() != null) {
            user.updateAddress(dto.getAddress());
        }
        return dto;
    }

}