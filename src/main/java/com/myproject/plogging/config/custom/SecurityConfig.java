package com.myproject.plogging.config.custom;


import com.myproject.plogging.config.jwt.JwtAccessDeniedHandler;
import com.myproject.plogging.config.jwt.JwtAuthenticationEntryPoint;
import com.myproject.plogging.config.jwt.JwtSecurityConfig;
import com.myproject.plogging.config.jwt.TokenProvider;
import com.myproject.plogging.config.oauth.PrincipalOauth2UserService;
import com.myproject.plogging.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    //private final PrincipalOauth2UserService principleOauth2UserService;

    private final TokenProvider tokenProvider;
    private final CorsFilter corsFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception {


        http
                .csrf((csrf) -> csrf.disable())

                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)

                .exceptionHandling(exceptionHandling -> exceptionHandling
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                )

                .cors(cors -> cors.disable())

                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(b -> b.disable())

                .authorizeHttpRequests(
        (authorizeHttpRequests) ->
                        authorizeHttpRequests
                               .requestMatchers(
                                       "/signup",
                                       "/photo/list",
                                       "/photo/like",
                                       "/meeting/list",
                                       "/meeting/info",
                                       "/people-count",
                                       "/api/authenticate",
                                       "/login").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(
        formLogin -> formLogin.disable())
                /* .oauth2Login(
         oauth2Login -> oauth2Login
                 .loginPage("/login")
                 .defaultSuccessUrl("http://localhost:3000")
                 .userInfoEndpoint(userInfoEndpointConfig ->
                         userInfoEndpointConfig.userService(principleOauth2UserService))
                 )
                 .logout(
         logout ->
                         logout.logoutUrl("/logout")
                                 .logoutSuccessUrl("/"))*/
                .headers(headers -> headers.frameOptions(options -> options.disable()))

                .apply(new JwtSecurityConfig(tokenProvider));


        return http.build();
    }

}
