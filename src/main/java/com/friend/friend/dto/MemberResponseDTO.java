package com.friend.friend.dto;

import com.friend.friend.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MemberResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinResultDTO{
        Long memberId;
        String status;
        String role;
        LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginResultDTO{
        Long memberId;
        String status;
        String role;
    }

    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member){
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .status(member.getStatus().toString())
                .role(member.getRole().toString())
                .createdAt(member.getCreatedAt())
                .build();
    }
    public static MemberResponseDTO.LoginResultDTO toLoginResultDTO(Member member){
        return LoginResultDTO.builder()
                .memberId(member.getId())
                .status(member.getStatus().toString())
                .role(member.getRole().toString())
                .build();
    }
}
