package com.friend.friend.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Array;

@RestController
public class MainController {

    @GetMapping("/login")
    public String Login() {
        return "login";
    }

    @GetMapping("/hello")
    public String helloWorld() {
        return "배포테스트입니다";
    }
    @GetMapping("/test")
    public String test(){
        return "테스트테스트채정훈222222222";
    }

    public String test2(){

        return "테스트";
    }
}
