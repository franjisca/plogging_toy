package com.myproject.plogging.controller;


import com.myproject.plogging.config.auth.PrincipalDetails;
import com.myproject.plogging.config.jwt.JwtFilter;
import com.myproject.plogging.config.jwt.TokenProvider;
import com.myproject.plogging.domain.User;
import com.myproject.plogging.dto.common.TokenDto;
import com.myproject.plogging.dto.user.LoginDto;
import com.myproject.plogging.dto.user.UserDataDto;
import com.myproject.plogging.dto.user.UserIdDto;
import com.myproject.plogging.dto.user.UserInfoChangeDto;
import com.myproject.plogging.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.HttpResource;
import jakarta.validation.Valid;

import java.net.http.HttpResponse;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;



    @GetMapping("/login-test")
    public String test(@AuthenticationPrincipal PrincipalDetails details) {
        System.out.println("principal details: "  + details);
        return "성공";
    }

    @PostMapping("/signup")
    public User signup(@RequestBody User user) {
        return userService.signup(user);
    }

    @PostMapping("/login2")
    public UserDataDto login(@Valid @RequestBody LoginDto loginDto, HttpServletResponse res) {
        User loginUser = userService.login(loginDto);

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

    @PostMapping("/login")
    public ResponseEntity<TokenDto> authorize(@RequestBody LoginDto loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUserId(), loginDto.getPassword());

        log.info("authenticationToken: {}", authenticationToken);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);

        org.springframework.http.HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/find-id/{userNo}")
    public UserIdDto findUserId(@PathVariable("userNo") Long userNo) {
        return userService.findByUserNo(userNo);
    }

}