package com.myproject.plogging.config.auth;


import com.myproject.plogging.domain.User;
import com.myproject.plogging.exception.UserNotFoundException;
import com.myproject.plogging.repository.user.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

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

