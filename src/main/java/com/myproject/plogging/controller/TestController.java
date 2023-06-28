package com.myproject.plogging.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/")
    public String test() {
        return "안녕하세요 지금 첫 api 호출을 했습니다";
    }

}
