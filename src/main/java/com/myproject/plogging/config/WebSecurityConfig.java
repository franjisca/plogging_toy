package com.myproject.plogging.config;


import com.myproject.plogging.config.jwt.JwtAuthenticationFilter;
import com.myproject.plogging.config.jwt.JwtAuthorizationFilter;
import com.myproject.plogging.config.oauth.PrincipleOauth2UserService;
import com.myproject.plogging.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig{

    private final CorsFilter corsFilter;

    private final UserRepository userRepository;

    private final PrincipleOauth2UserService principleOauth2UserService;

    private final CustomBcryptEncoder bcryptEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception {
        http.csrf((csrf) -> csrf.disable());
        http.sessionManagement(httpSecuritySessionManagementConfigurer ->
                httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
       // http.apply(new MyCustomDsl());
        http.authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .requestMatchers("/mypage/**").authenticated()
                                .anyRequest().permitAll()
                ).formLogin(formLogin ->
                formLogin.loginPage("/login-page")
                        .usernameParameter("userId")
                        .passwordParameter("password")
                        .loginProcessingUrl("/login") // login 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인 진행
                        .permitAll())
                .oauth2Login(oauth2Login -> oauth2Login
                        .loginPage("/login")
                        .defaultSuccessUrl("http://localhost:3000")
                        // .failureUrl("http://localhost:3000/login-page")
                        .userInfoEndpoint(userInfoEndpointConfig ->
                                userInfoEndpointConfig.userService(principleOauth2UserService))

                ).logout(logout ->
                        logout.logoutUrl("/logout")
                                .logoutSuccessUrl("/")

                )
        ;
        return http.build();
    }

    class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity> {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
            http
                    .addFilter(corsFilter)
                    .addFilter(new JwtAuthenticationFilter(authenticationManager))
                    .addFilter(new JwtAuthorizationFilter(authenticationManager, userRepository));
        }
    }
}
