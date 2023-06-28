package com.myproject.plogging.controller;


import com.myproject.plogging.domain.User;
import com.myproject.plogging.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;


    @PostMapping("/signup")
    public User signup(@ModelAttribute User user) {

        User saveUser = userRepository.save(user);

        return user;
    }

}
