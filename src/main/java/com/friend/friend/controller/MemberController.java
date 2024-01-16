package com.friend.friend.controller;

import com.friend.friend.domain.Member;
import com.friend.friend.dto.MemberRequestDTO;
import com.friend.friend.dto.MemberResponseDTO;
import com.friend.friend.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;




@Configuration
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    private final PasswordEncoder passwordEncoder;

    @GetMapping("member/insert")
    public void insertMember(){
//        memberService.insertMember();
    }

    @PostMapping("/users")
    public MemberResponseDTO.JoinResultDTO joinMember(@RequestBody MemberRequestDTO.MemberJoinDTO request){
        Member member = Member.toMember(request, passwordEncoder);
        memberService.join(member);

        return MemberResponseDTO.toJoinResultDTO(member);
    }

}
