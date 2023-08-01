package com.myproject.plogging.config.custom;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component("passwordEncoder")
public class CustomBcryptEncoder extends BCryptPasswordEncoder {

}
