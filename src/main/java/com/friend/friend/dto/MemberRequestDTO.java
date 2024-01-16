package com.friend.friend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;


public class MemberRequestDTO {
    @Getter
    public static class MemberJoinDTO{
        @Email
        String email;
        String password;
        Integer role; // 0 : USER, 1 : ADMIN
        String nickname;
        String phone;
        String birthday;
        Integer gender; // 0 : FEMALE, 1 : MALE
        Double height;
        String region;
        String department;
        Integer distance; // 0 : LONG(장거리 가능), 1 : SHORT
        Integer smoking; // 0 : SMOKER, 1 : NONSMOKER
        Integer drinking; // 0 : DRINKER, 1 : NONDRINKER
        String introduction;
        String preference;
        String nondepartment;
        String nonstudentid;
        String nonage;
        String image;
    }
}
