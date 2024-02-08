package com.friend.friend.controller;

import com.friend.friend.domain.Member;
import com.friend.friend.domain.enums.*;
import com.friend.friend.dto.MemberRequestDTO;
import com.friend.friend.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.Array;

@RestController
@RequiredArgsConstructor
public class MainController {


    private final MemberService memberService;

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
        return "테스트테스트채정2222222222";
    }

    @PostMapping("/login")
    public Long ghldnjsrkdlq(@RequestBody MemberRequestDTO.MemberJoinDTO request){
        RoleEnum role = (request.getRole() == 0) ? RoleEnum.USER : RoleEnum.ADMIN;
        GenderEnum gender = (request.getGender() == 0) ? GenderEnum.FEMALE : GenderEnum.MALE;
        DistanceEnum distance = (request.getDistance() == 0) ? DistanceEnum.LONG : DistanceEnum.SHORT;
        SmokingEnum smoking = (request.getSmoking() == 0) ? SmokingEnum.SMOKER : SmokingEnum.NONSMOKER;
        DrinkingEnum drinking = (request.getDrinking() == 0) ? DrinkingEnum.DRINKER : DrinkingEnum.NONDRINKER;

        Member member = Member.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .status(AccountStatusEnum.ACTIVE)
                .role(role)
                .nickname(request.getNickname())
                .phone(request.getPhone())
                .birthday(request.getBirthday())
                .gender(gender)
                .height(request.getHeight())
                .region(request.getRegion())
                .department(request.getDepartment())
                .distance(distance)
                .smoking(smoking)
                .drinking(drinking)
                .introduction(request.getIntroduction())
                .preference(request.getPreference())
                .nondepartment(request.getNondepartment())
                .nonstudentid(request.getNonstudentid())
                .nonage(request.getNonage())
                .nonRegion(request.getNonRegion())
                .build();
        System.out.println(member.getId());
        System.out.println(member.getHeight());

        Long join = memberService.join(member);
        return join;
    }
    public String test2(){

        return "테스트";
    }
}
