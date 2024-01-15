package com.myproject.plogging.service;


import com.myproject.plogging.config.auth.PrincipalDetails;
import com.myproject.plogging.domain.User;
import com.myproject.plogging.dto.user.LoginDto;
import com.myproject.plogging.dto.user.UserDataDto;
import com.myproject.plogging.dto.user.UserIdDto;
import com.myproject.plogging.dto.user.UserInfoChangeDto;
import com.myproject.plogging.exception.UserNotFoundException;
import com.myproject.plogging.repository.user.UserRepository;
import com.myproject.plogging.repository.user.plasticbag.RedisRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService{
    private final UserRepository userRepository;

    private final PasswordEncoder bcryptEncoder;

    private final RedisRepositoryImpl redisRepository;

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

        if(!dto.getPassword().isEmpty()) {
            System.out.println("getChangePassword: " + dto.getPassword());
            user.updatePassword(bcryptEncoder.encode(dto.getPassword()));
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

    @Transactional
    public void plasticbacCount(String userId) {

        User user = userRepository.findByUserStrId(userId);

        user.incrementPlasticBagCount();

    }

    public UserDataDto getMyInfo(String userId){
        User findUser = userRepository.findByUserStrId(userId);

        return new UserDataDto().builder()
                .userId(findUser.getUserId())
                .username(findUser.getUsername())
                .nickname(findUser.getNickname())
                .password(findUser.getPassword())
                .email(findUser.getEmail())
                .phone(findUser.getPhone())
                .address(findUser.getAddress())
                .build();
    }

    public UserIdDto findByUserNo(Long userNo) {
        return userRepository.findByUserNo(userNo);
    }


    public Integer getMyPlasticCount(String userId) {

        User user = userRepository.findByUserStrId(userId);

        return user.getPlasticBagCount();
    }


    @Transactional
    @Async
    public void addCountPlasticBag(String userId) {


        User user = userRepository.findByUserStrId(userId);

        if(redisRepository.getCount() > 0) {
        redisRepository.decrease();
        user.incrementPlasticBagCount();
        } else {
        throw new IllegalArgumentException("요청을 수행할 수 없습니다.");
        }


    }


    public int totalPlasticBag(){
        return redisRepository.getCount();
    }
}
