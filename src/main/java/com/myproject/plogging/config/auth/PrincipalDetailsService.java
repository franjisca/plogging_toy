package com.myproject.plogging.config.auth;


import com.myproject.plogging.domain.User;
import com.myproject.plogging.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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

        User user = userRepository.findByUserid(userId);

        if(user == null) {
            throw new UsernameNotFoundException("user not found");
        }

        return new PrincipalDetails(user);
    }

}

