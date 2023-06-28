package com.myproject.plogging.controller;


import com.myproject.plogging.config.CustomBcryptEncoder;
import com.myproject.plogging.domain.User;
import com.myproject.plogging.dto.user.LoginDto;
import com.myproject.plogging.dto.user.UserInfoChangeDto;
import com.myproject.plogging.exception.UserNotFoundException;
import com.myproject.plogging.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
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

    @GetMapping("/test/info-change")
    public String infoChange(@RequestParam("userId") String userId){

/*        User user = userRepository.findByUserid(userId);

        if(user == null) {
            throw new IllegalArgumentException("변경할 수 없음");
        }*/

        return userId;
    }

}