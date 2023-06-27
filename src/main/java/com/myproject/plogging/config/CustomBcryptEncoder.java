package com.myproject.plogging.config;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomBcryptEncoder extends BCryptPasswordEncoder {

}
