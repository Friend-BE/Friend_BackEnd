package com.friend.friend.controller;

import com.friend.friend.domain.Member;
import com.friend.friend.dto.MemberRequestDTO;
import com.friend.friend.dto.MemberResponseDTO;
import com.friend.friend.service.FireBaseService;
import com.friend.friend.service.MemberService;
import com.google.firebase.auth.FirebaseAuthException;
import java.util.List;
import java.util.Optional;
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

    @GetMapping("member/insert")
    public void insertMember() {
//        memberService.insertMember();
    }

    @PostMapping("/users")
    public MemberResponseDTO.JoinResultDTO joinMember(
            @RequestPart(value = "request") MemberRequestDTO.MemberJoinDTO request,
            @RequestPart("image") MultipartFile file) throws IOException, FirebaseAuthException {
        String imgUrl = fireBaseService.uploadFiles(file, request.getNickname());
        Member member = Member.toMember(request, passwordEncoder, imgUrl);
        memberService.join(member);

        return MemberResponseDTO.toJoinResultDTO(member);
    }

    @PostMapping("/login")
    public MemberResponseDTO.LoginResultDTO loginMember(@RequestBody MemberRequestDTO.LoginMemberDTO request)
            throws Exception {
        List<Member> member = memberService.findByEmail(request.getEmail());

        if (member.size() == 0) {
            return null;
        } else {
            if (passwordEncoder.matches(request.getPassword(), member.get(0).getPassword())) {
                return MemberResponseDTO.toLoginResultDTO(member.get(0));
            }
        }

        return null;
    }
}
