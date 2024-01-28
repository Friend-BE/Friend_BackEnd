package com.friend.friend.dto;

import com.friend.friend.domain.Member;
import com.friend.friend.domain.enums.DistanceEnum;
import com.friend.friend.domain.enums.DrinkingEnum;
import com.friend.friend.domain.enums.SmokingEnum;
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
    public static class profileDTO{
        String nickname;
        String birthday;
        double height;
        String region;
        DistanceEnum distance;
        SmokingEnum smoking;
        DrinkingEnum drinking;
        String department;
        String introduction;
        String imgUrl;
    }

    
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
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class activateResultDTO{
        String nickname;
        String birthday;
        double height;
        String region;
        DistanceEnum distance;
        SmokingEnum smoking;
        DrinkingEnum drinking;
        String department;
        String introduction;
        String imgUrl;
        String email;
        String nondepartment;
        String nonstudentid;
        String nonage;
        String nonRegion;
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
