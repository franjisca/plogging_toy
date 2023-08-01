package com.myproject.plogging.service;

import com.myproject.plogging.config.auth.PrincipalDetails;
import com.myproject.plogging.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component("userDetailsService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public PrincipalDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        return new PrincipalDetails(userRepository.findByUserStrId(userId));
    }




}
