package com.friend.friend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/login")
    public String Login() {
        return "loginPage";
    }

    @GetMapping("/hello")
    public String helloWorld() {
        return "배포테스트입니다";
    }
}