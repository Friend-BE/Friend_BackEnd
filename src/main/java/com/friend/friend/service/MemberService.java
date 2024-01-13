package com.friend.friend.service;

import com.friend.friend.domain.ApiResponse;
import com.friend.friend.domain.Member;
import com.friend.friend.domain.Post;
import com.friend.friend.domain.Qa;
import com.friend.friend.domain.enums.*;
import com.friend.friend.repository.MemberRepository;
import com.friend.friend.repository.PostRepository;
import com.friend.friend.repository.QaRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Builder
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final QaRepository qaRepository;

    public ApiResponse login(String email, String password){
        Member member = memberRepository.findByEmail(email);
        ApiResponse apiResponse = new ApiResponse();
        if(member==null){
            apiResponse.setStatus(HTTPResponseEnum.BAD_REQUEST);
            apiResponse.setMessage("The membership registration history does not exist.");
        }else{
            String memberPW = member.getPassword();
            if(password.equals(memberPW)){
                apiResponse.setMessage("Login Success");
                apiResponse.setStatus(HTTPResponseEnum.OK);
            }else{
                apiResponse.setMessage("Password is different");
                apiResponse.setStatus(HTTPResponseEnum.BAD_REQUEST);
            }
        }
        return apiResponse;
    }
    public ApiResponse  findEmail(String number){
        Member member = memberRepository.findByPhone(number);
        ApiResponse apiResponse = new ApiResponse();
        if(member==null){
            apiResponse.setMessage("동일한 번호로 회원가입된 내역이 없습니다.");
            apiResponse.setStatus(HTTPResponseEnum.BAD_REQUEST);
        }else{
            String email = member.getEmail();
            apiResponse.setMessage(email);
            apiResponse.setStatus(HTTPResponseEnum.OK);
        }
        return apiResponse;
    }
    public ApiResponse findPassword(String email, String number){
        Member member = memberRepository.findByEmail(email);
        ApiResponse apiResponse = new ApiResponse();
        if(member==null){
            apiResponse.setMessage("서버 오류");
            apiResponse.setStatus(HTTPResponseEnum.BAD_REQUEST);
        }else{
            String memberPhone = member.getPhone();
            if(memberPhone.equals(number)){
               apiResponse.setMessage(member.getPassword());
               apiResponse.setStatus(HTTPResponseEnum.OK);
            }else{
                apiResponse.setMessage("등록된 번호가 다릅니다");
                apiResponse.setStatus(HTTPResponseEnum.BAD_REQUEST);
            }
        }
        return apiResponse;
    }
}
