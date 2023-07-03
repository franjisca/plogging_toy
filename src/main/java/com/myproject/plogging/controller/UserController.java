package com.myproject.plogging.controller;


import com.myproject.plogging.config.auth.PrincipalDetails;
import com.myproject.plogging.domain.User;
import com.myproject.plogging.dto.user.LoginDto;
import com.myproject.plogging.dto.user.UserInfoChangeDto;
import com.myproject.plogging.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public User signup(@RequestBody User user) {
        return userService.signup(user);
    }

    @PostMapping("/login-page")
    public User login(@RequestBody LoginDto loginDto) {
        return userService.login(loginDto);
    }

    @GetMapping("/oidc-principal")
    public OidcUser loginOauth(@AuthenticationPrincipal PrincipalDetails principalDetails){

        System.out.println("SecurityContextHolder: " + SecurityContextHolder.getContext().getAuthentication());

        if(principalDetails != null) {

        return (OidcUser) principalDetails;
        }

        return null;
    }

    @PatchMapping ("/info-change/{userId}")
    public UserInfoChangeDto infoChange(@PathVariable("userId") String userId, @RequestBody UserInfoChangeDto dto){

        return userService.infoChange(userId, dto);
    }

}