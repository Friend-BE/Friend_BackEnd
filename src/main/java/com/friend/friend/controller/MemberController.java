package com.friend.friend.controller;

import com.friend.friend.controller.DTO.MemberResponseDTO;
import com.friend.friend.domain.Member;
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

    @GetMapping("member/insert")
    public void insertMember(){
        memberService.insertMember();
    }


    /**
     * 마이페이지 - 프로필카드확인 api
     * jwt쓴다면 email을 jwt토큰안에서 꺼내와도될듯?
     * APIResponse설정필요, 예외처리필요
     */
    @GetMapping("myPage/getProfile/{email}")
    public MemberResponseDTO.profileDTO getProfile(@PathVariable String email){
        System.out.println(email);
        Member member = memberService.getMemberByEmail(email);

        MemberResponseDTO.profileDTO memberProfile = MemberResponseDTO.profileDTO.builder()
                .distance(member.getDistance())
                .birthday(member.getBirthday())
                .height(member.getHeight())
                .region(member.getRegion())
                .smoking(member.getSmoking())
                .drinking(member.getDrinking())
                .introduction(member.getIntroduction())
                .nickname(member.getNickname())
                .department(member.getDepartment())
                .build();

        return memberProfile;
    }

}
