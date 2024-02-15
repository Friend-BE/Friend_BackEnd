package com.friend.friend.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProfileEditRequestDTO {
    private Integer role; // 0 : USER, 1 : ADMIN
    private String nickname;
    private String phone;
    private String birthday;
    private Integer gender; // 0 : FEMALE, 1 : MALE
    private Double height;
    private String region;
    private String department;
    private Integer distance; // 0 : LONG(장거리 가능), 1 : SHORT
    private Integer smoking; // 0 : SMOKER, 1 : NONSMOKER
    private Integer drinking; // 0 : DRINKER, 1 : NONDRINKER
    private String introduction;
    private String preference;
    private String nonRegion;
    private String nondepartment;
    private String nonstudentid;
    private String nonage;
}
