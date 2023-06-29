package com.myproject.plogging.config.auth;

import com.myproject.plogging.domain.User;
import com.myproject.plogging.exception.UserNotFoundException;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Data
public class PrincipalDetails implements UserDetails, OAuth2User{

    private User user;
    private Map<String ,Object> attributes;

    //일반 로그인에 사용
    public PrincipalDetails(User user) {
        this.user = user;
    }

    //oauth 로그인에 사용
    public PrincipalDetails(User user, Map<String, Object> attributes) {
        this.user = user;
        this.attributes = attributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 사이트에서 1년 동안 회원이 로그인을 안 하면 휴면 계정으로 지정
        // 현재 시간 - 로그인 시간 => 1년 초과시 false
        return true;
    }

    @Override
    public String getName() {
        return null;
    }
}
