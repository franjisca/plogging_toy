package com.myproject.plogging.config.jwt;


// 시큐리티가 filter를 가지고 있는데 그 필터중 BasicAuthenticationFilter라는 것
// 권한이나 인증이 필요한 특정 주소를 요청했을 때 위 필터를 무조건 타게 되어 있음
// 만약 권한이 인증이 필요한 주소가 아니라면 이 필터를 거치지 않음

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.myproject.plogging.config.auth.PrincipalDetails;
import com.myproject.plogging.domain.User;
import com.myproject.plogging.repository.user.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final UserRepository userRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
        System.out.println("인증이나 권한이 필요한 주소 요청이 됨");

    }


    // 인증이나 권한이 필요한 주소 요청이 있을 떄 해당 필터를 타게 됨
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        super.doFilterInternal(request, response, chain);
        String JwtHeader = request.getHeader("Authorization");
        System.out.println("JwtHeader: " + JwtHeader);


        // 헤더 확인하기
        if(JwtHeader == null || !JwtHeader.startsWith("Bearer")) {
            chain.doFilter(request, response);
            return;
        }

        // 토큰 검증을 통해 정상적인 사용자 유무 확인
        String token = request.getHeader("Authorization").replace("token", "");

        String userId = JWT.require(Algorithm.HMAC512("cos")).build().verify(token).getClaim("username").asString();

        if(userId != null){

            User user = userRepository.findByUserStrId(userId);
            PrincipalDetails principalDetails = new PrincipalDetails(user);

            // 토큰 서명을 통해서 서명이 정상이면 Authentication 객체를 만들어줌
            Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());

            // 강제로 시큐리티의 세션에 접근하여 Authentication 객체 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }
}
