package com.friend.friend.controller;

import com.friend.friend.domain.ApiResponse;
import com.friend.friend.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

@Configuration
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("member/login")
    public ApiResponse login(@RequestParam String email, String password){
        return memberService.login(email,password);
    }
    @GetMapping("member/findEmail")
    public ApiResponse findEmail(@RequestParam String number){
        return memberService.findEmail(number);
    }
    @GetMapping("member/findPw")
    public ApiResponse findPassword(@RequestParam String email,@RequestParam String number){
        return memberService.findPassword(email,number);
    }
}