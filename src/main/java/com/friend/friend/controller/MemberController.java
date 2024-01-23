package com.friend.friend.controller;

import com.friend.friend.domain.Member;
import com.friend.friend.dto.MemberRequestDTO;
import com.friend.friend.dto.MemberResponseDTO;
import com.friend.friend.service.FireBaseService;
import com.friend.friend.service.MemberService;
import com.google.firebase.auth.FirebaseAuthException;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Configuration
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final FireBaseService fireBaseService;
    private final PasswordEncoder passwordEncoder;
    @Operation(summary = "회원가입")
    @PostMapping("/users")
    public MemberResponseDTO.JoinResultDTO joinMember(
            @RequestPart(value = "request") MemberRequestDTO.MemberJoinDTO request,
            @RequestPart("image") MultipartFile file) throws IOException, FirebaseAuthException {
        String imgUrl = fireBaseService.uploadFiles(file, request.getNickname());
        Member member = Member.toMember(request, passwordEncoder, imgUrl);
        memberService.join(member);

        return MemberResponseDTO.toJoinResultDTO(member);
    }

    @Operation(summary = "로그인")
    @PostMapping("/login")
    public MemberResponseDTO.LoginResultDTO loginMember(@RequestBody MemberRequestDTO.LoginMemberDTO request)
            throws Exception {
        List<Member> member = memberService.findByEmail(request.getEmail());

        if (member.isEmpty()) {
            return null;
        } else {
            if (passwordEncoder.matches(request.getPassword(), member.get(0).getPassword())) {
                return MemberResponseDTO.toLoginResultDTO(member.get(0));
            }
        }

        return null;
    }

    /**
     * 마이페이지 - 프로필카드확인 api
     * jwt쓴다면 email을 jwt토큰안에서 꺼내와도될듯?
     * APIResponse설정필요, 예외처리필요
     */
    @Operation(summary = "프로필 카드 가져오기")
    @GetMapping("myPage/getProfile/{email}")
    public MemberResponseDTO.profileDTO getProfile(@PathVariable String email){
        System.out.println(email);
        Member member = memberService.getMemberByEmail(email);

        return MemberResponseDTO.profileDTO.builder()
                .distance(member.getDistance())
                .birthday(member.getBirthday())
                .height(member.getHeight())
                .region(member.getRegion())
                .smoking(member.getSmoking())
                .drinking(member.getDrinking())
                .introduction(member.getIntroduction())
                .nickname(member.getNickname())
                .department(member.getDepartment())
                .imgUrl(member.getImage())
                .build();
    }
}
