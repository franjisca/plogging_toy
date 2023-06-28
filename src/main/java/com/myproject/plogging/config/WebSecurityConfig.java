package com.myproject.plogging.config;


import com.myproject.plogging.config.oauth.PrincipleOauth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig{

    private final PrincipleOauth2UserService principleOauth2UserService;

    private final CustomBcryptEncoder bcryptEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception {
        http.csrf((csrf) -> csrf.disable());
        http.authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .requestMatchers("/user/**").authenticated()
                                .anyRequest().permitAll()
                ).formLogin(formLogin ->
                formLogin.loginPage("/loginForm")
                        .loginProcessingUrl("/login") // login 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인 진행
                        .defaultSuccessUrl("/")
                        .permitAll())
                .oauth2Login(oauth2Login -> oauth2Login.loginPage("/loginForm")
                        .userInfoEndpoint(userInfoEndpointConfig ->
                                userInfoEndpointConfig.userService(principleOauth2UserService))
                );
        return http.build();
    }
}
