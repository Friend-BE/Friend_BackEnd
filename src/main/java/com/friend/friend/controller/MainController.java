package com.friend.friend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public String Basic() {
        return "베이직테스트입니다";
    }

    @GetMapping("/hello")
    public String helloWorld() {
        return "배포테스트입니다";
    }
}
