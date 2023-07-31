package com.myproject.plogging.config.custom;


import com.myproject.plogging.config.jwt.JwtAuthenticationFilter;
import com.myproject.plogging.config.oauth.PrincipalOauth2UserService;
import com.myproject.plogging.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserRepository userRepository;
    private final PrincipalOauth2UserService principleOauth2UserService;

    private final CustomBcryptEncoder bcryptEncoder;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception {

        // http.apply(MyCustomDsl.customDsl());
        http.csrf((csrf) -> csrf.disable())
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
                                        "/photo/like/**",
                                        "/meeting/list",
                                        "/meeting/info",
                                        "/people-count"
                                        ).permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(
        formLogin -> formLogin.disable())
                .oauth2Login(
        oauth2Login -> oauth2Login
                .loginPage("/login")
                .defaultSuccessUrl("http://localhost:3000")
                .userInfoEndpoint(userInfoEndpointConfig ->
                        userInfoEndpointConfig.userService(principleOauth2UserService))
                )
                .logout(
        logout ->
                        logout.logoutUrl("/logout")
                                .logoutSuccessUrl("/"));
        return http.getOrBuild();
    }

    public static class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity> {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
            http
                    .addFilter(new JwtAuthenticationFilter(authenticationManager));
        }
        public static MyCustomDsl customDsl() {
            return new MyCustomDsl();
        }

    }

}
