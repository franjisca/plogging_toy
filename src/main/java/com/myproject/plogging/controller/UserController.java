package com.myproject.plogging.controller;


import com.myproject.plogging.config.CustomBcryptEncoder;
import com.myproject.plogging.domain.User;
import com.myproject.plogging.dto.user.LoginDto;
import com.myproject.plogging.dto.user.UserInfoChangeDto;
import com.myproject.plogging.exception.UserNotFoundException;
import com.myproject.plogging.repository.user.UserRepository;
import com.myproject.plogging.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public User signup(@RequestBody User user) {
        return userService.signup(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody LoginDto loginDto) {
        return userService.login(loginDto);
    }


    // 나중에
    @PatchMapping ("/info-change/{userId}")
    public UserInfoChangeDto infoChange(@PathVariable("userId") String userId, @RequestBody UserInfoChangeDto dto){

        return userService.infoChange(userId, dto);
    }

}