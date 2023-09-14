package com.myproject.plogging.service;

import com.myproject.plogging.config.auth.PrincipalDetails;
import com.myproject.plogging.domain.User;
import com.myproject.plogging.exception.UserNotFoundException;
import com.myproject.plogging.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component("userDetailsService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        User user = userRepository.findByUserStrId(userId);

        if(user == null) {
            throw new UserNotFoundException(userId+"를 찾을 수 없습니다");
        }

        return new PrincipalDetails(user);
    }


}
