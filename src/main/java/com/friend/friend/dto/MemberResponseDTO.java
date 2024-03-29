package com.friend.friend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.friend.friend.domain.Member;
import com.friend.friend.domain.enums.*;
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
        String phone;
        String preference;
        DistanceEnum distance;
        SmokingEnum smoking;
        DrinkingEnum drinking;
        String department;
        String introduction;
        String imgUrl;
        String nonDepartment;
        String nonRegion;
        String nonAge;
        String nonStudentId;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class successDeleteDTO{
        String success;
        Long id;
        String email;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class statusInactiveDTO{
        String success;
        Long id;
        String email;
        AccountStatusEnum accountStatusEnum;
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
        String email;
        String nickname;
        String role;
    }
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class activateResultDTO{
        String nickname;
        GenderEnum gender;
        String email;
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
        LocalDateTime time;
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
                .email(member.getEmail())
                .memberId(member.getId())
                .status(member.getStatus().toString())
                .nickname(member.getNickname())
                .role(member.getRole().toString())
                .build();
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class memberListDTO{
        Long memberId;
        String nickname;
        GenderEnum gender;
        LocalDateTime createdAt;

        public memberListDTO(Member member) {
            this.memberId = member.getId();
            this.nickname = member.getNickname();
            this.gender = member.getGender();
            this.createdAt = member.getCreatedAt();
        }
    }
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReportResponseDTO{
        Long memberId;
        String nickname;
        int count;
    }
}
