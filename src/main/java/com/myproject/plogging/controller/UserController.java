package com.myproject.plogging.controller;


import com.myproject.plogging.config.auth.PrincipalDetails;
import com.myproject.plogging.domain.User;
import com.myproject.plogging.dto.user.LoginDto;
import com.myproject.plogging.dto.user.UserDataDto;
import com.myproject.plogging.dto.user.UserInfoChangeDto;
import com.myproject.plogging.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.HttpResource;

import java.net.http.HttpHeaders;
import java.net.http.HttpResponse;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/login-test")
    public String test(@AuthenticationPrincipal PrincipalDetails details) {
        System.out.println("principal details: "  + details);
        return "성공";
    }

    @PostMapping("/signup")
    public User signup(@RequestBody User user) {
        return userService.signup(user);
    }

    @PostMapping("/login")
    public UserDataDto login(@RequestBody LoginDto loginDto, HttpServletResponse res) {
        User loginUser = userService.login(loginDto);

        res.addHeader("accessToken", "hi");
        return new UserDataDto().builder()
                .userId(loginUser.getUserId())
                .username(loginUser.getUsername())
                .nickname(loginUser.getNickname())
                .password(loginUser.getPassword())
                .email(loginUser.getEmail())
                .phone(loginUser.getPhone())
                .address(loginUser.getAddress())
                .build();
    }


}