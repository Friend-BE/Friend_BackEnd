package com.friend.friend.controller;

import com.friend.friend.common.Response;
import com.friend.friend.domain.Member;
import com.friend.friend.dto.MemberRequestDTO;
import com.friend.friend.dto.MemberResponseDTO;
import com.friend.friend.dto.SuccessResponseDto;
import com.friend.friend.service.FireBaseService;
import com.friend.friend.service.MemberService;
import com.google.firebase.auth.FirebaseAuthException;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nullable;
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
    public ResponseEntity joinMember(
            @RequestPart(value = "request") MemberRequestDTO.MemberJoinDTO request,
             @RequestPart(value = "image",required = false) MultipartFile file) throws IOException, FirebaseAuthException {
        String imgUrl = fireBaseService.uploadFiles(file, request.getEmail());

        Member member = Member.toMember(request, passwordEncoder, imgUrl);
        memberService.join(member);
        MemberResponseDTO.JoinResultDTO resultDTO = MemberResponseDTO.toJoinResultDTO(member);
        if(resultDTO!=null){
            return new ResponseEntity(Response.success(resultDTO), HttpStatus.OK);
        }else {
            return new ResponseEntity(Response.failure(),HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(summary = "심사 끝난 계정 활성화")
    @PostMapping("/activate")
    public ResponseEntity activateAccount(
            @RequestBody MemberRequestDTO.AccountUpdateDTO request
    ){
        memberService.activateAccount(request.getEmail());
        Optional<Member> member = memberService.findByEmail(request.getEmail());
        MemberResponseDTO.JoinResultDTO resultDTO = MemberResponseDTO.toJoinResultDTO(member.get());
        if(resultDTO!=null){
            return new ResponseEntity(Response.success(resultDTO), HttpStatus.OK);
        }else{
            return new ResponseEntity(Response.failure(),HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "심사 대기중인 회원 리스트 가져오기")
    @GetMapping("/activateList")
    public ResponseEntity activateList(){
        try{
            List<Member> activateList = memberService.findAuditList();
            List<MemberResponseDTO.activateResultDTO> resultDTO = new ArrayList<>();
            for (Member member : activateList) {
                MemberResponseDTO.activateResultDTO result = MemberResponseDTO.activateResultDTO.builder()
                        .distance(member.getDistance())
                        .birthday(member.getBirthday())
                        .height(member.getHeight())
                        .region(member.getRegion())
                        .smoking(member.getSmoking())
                        .drinking(member.getDrinking())
                        .introduction(member.getIntroduction())
                        .nickname(member.getNickname())
                        .department(member.getDepartment())
                        .imgUrl(member.getImgUrl())
                        .email(member.getEmail())
                        .nondepartment(member.getNondepartment())
                        .nonRegion(member.getNonRegion())
                        .nonstudentid(member.getNonstudentid())
                        .nonage(member.getNonage())
                        .build();
                resultDTO.add(result);
            }
            return new ResponseEntity(Response.success(resultDTO), HttpStatus.OK);

        }catch (IllegalArgumentException ex){
            return new ResponseEntity(Response.failure(),HttpStatus.BAD_REQUEST);
        }

    }
    @Operation(summary = "로그인")
    @PostMapping("/login")
    public ResponseEntity loginMember(@RequestBody MemberRequestDTO.LoginMemberDTO request)
            throws Exception {
        Optional<Member> member = memberService.findByEmail(request.getEmail());

        if (member.isEmpty()) {
            return new ResponseEntity(Response.failure(),HttpStatus.BAD_REQUEST);
        } else {
            if (passwordEncoder.matches(request.getPassword(), member.get().getPassword())) {
                MemberResponseDTO.LoginResultDTO loginResultDTO = MemberResponseDTO.toLoginResultDTO(member.get());
                return new ResponseEntity(Response.success(loginResultDTO),HttpStatus.OK);
            }
        }
        return new ResponseEntity(Response.failure(),HttpStatus.BAD_REQUEST);
    }
    /**
     * 마이페이지 - 프로필카드확인 api
     * jwt쓴다면 email을 jwt토큰안에서 꺼내와도될듯?
     * APIResponse설정필요, 예외처리필요
     */
    @Operation(summary = "프로필 카드 가져오기")
    @GetMapping("myPage/getProfile/{email}")
    public ResponseEntity getProfile(@PathVariable String email){
        Member member = memberService.getMemberByEmail(email);

        MemberResponseDTO.profileDTO profileDTO = MemberResponseDTO.profileDTO.builder()
                .distance(member.getDistance())
                .birthday(member.getBirthday())
                .height(member.getHeight())
                .region(member.getRegion())
                .smoking(member.getSmoking())
                .drinking(member.getDrinking())
                .introduction(member.getIntroduction())
                .nickname(member.getNickname())
                .department(member.getDepartment())
                .imgUrl(member.getImgUrl())
                .build();
        if(profileDTO!=null){
            return new ResponseEntity(Response.success(profileDTO),HttpStatus.OK);
        }else{
            return new ResponseEntity(Response.failure(),HttpStatus.BAD_REQUEST);
        }
    }


    @Operation(summary = "회원 목록 가져오기")
    @GetMapping("/memberList")
    public ResponseEntity getMemberList(@RequestParam Integer gender,
                                        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") String date){
        try {
            List<Member> memberList = memberService.memberList(gender, date);
            List<MemberResponseDTO.memberListDTO> result = new ArrayList<>();

            for (Member member : memberList) {
                MemberResponseDTO.memberListDTO resultDTO = MemberResponseDTO.memberListDTO.builder()
                        .memberId(member.getId())
                        .nickname(member.getNickname())
                        .gender(member.getGender())
                        .createdAt(member.getCreatedAt())
                        .build();
                result.add(resultDTO);
            }

            return new ResponseEntity(Response.success(result), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(Response.failure(),HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "탈퇴하기")
    @DeleteMapping("/member/{email}")
    public ResponseEntity DeleteMember(@PathVariable String email){
        try{
            MemberResponseDTO.successDeleteDTO successDeleteDTO = memberService.deleteMember(email);
            return new ResponseEntity(Response.success(successDeleteDTO),HttpStatus.OK);
        }catch (IllegalArgumentException ex){
            return new ResponseEntity(Response.failure(),HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "비활성화하기")
    @PatchMapping("/member/status/{email}")
    public ResponseEntity changeStatusMember(@PathVariable String email){
        try{
            MemberResponseDTO.statusInactiveDTO statusInactiveDTO = memberService.inActiveMember(email);
            return new ResponseEntity(Response.success(statusInactiveDTO),HttpStatus.OK);
        }catch(IllegalArgumentException ex){
            return new ResponseEntity(Response.failure(),HttpStatus.BAD_REQUEST);
        }
    }
}
