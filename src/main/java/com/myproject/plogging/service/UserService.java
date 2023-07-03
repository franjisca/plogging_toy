package com.myproject.plogging.service;


import com.myproject.plogging.config.CustomBcryptEncoder;
import com.myproject.plogging.config.auth.PrincipalDetails;
import com.myproject.plogging.domain.User;
import com.myproject.plogging.dto.user.LoginDto;
import com.myproject.plogging.dto.user.UserInfoChangeDto;
import com.myproject.plogging.exception.UserNotFoundException;
import com.myproject.plogging.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    private final CustomBcryptEncoder bcryptEncoder;

    @Transactional
    public User signup(User user) {



        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        User saveUser = userRepository.save(user);

        return saveUser;
    }

    @Transactional
    public User login(LoginDto loginDto) {

        User user = userRepository.findByUserIdAndPwd(loginDto);

        if(user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", loginDto.getUserId()));
        }

        return user;
    }

    @Transactional
    public UserInfoChangeDto infoChange(String userId, UserInfoChangeDto dto){

        User user = userRepository.findByUserStrId(userId);

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

   @Transactional
    public User oauthLogin(PrincipalDetails principalDetails) {

        LoginDto loginDto = new LoginDto(principalDetails.getUser().getUserId(), principalDetails.getAttribute("password"));

        User user = userRepository.findByUserIdAndPwd(loginDto);


        if(user == null) {
            throw new UserNotFoundException(String.format("ID [%s]를 찾을 수 없습니다.  아이디 또는 비밀번호를 제대로 입력해주세요.", loginDto.getUserId()));
        }

        return user;
    }


    @Override
    public PrincipalDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        PrincipalDetails principalDetails = new PrincipalDetails(userRepository.findByUserStrId(userId));
        return principalDetails;
    }

}
