package com.myproject.plogging.config.custom;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class CustomBcryptEncoder extends BCryptPasswordEncoder {

}
