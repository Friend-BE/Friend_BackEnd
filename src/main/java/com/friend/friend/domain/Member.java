package com.friend.friend.domain;

import com.fasterxml.jackson.databind.deser.DataFormatReaders.Match;
import com.friend.friend.common.BaseEntity;
import com.friend.friend.domain.enums.*;
import com.friend.friend.dto.MemberRequestDTO;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private AccountStatusEnum status;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    private String nickname;

    private String phone;

    private String birthday;

    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    private double height;

    private String region;

    private String department;

    @Enumerated(EnumType.STRING)
    private DistanceEnum distance;

    @Enumerated(EnumType.STRING)
    private SmokingEnum smoking;

    @Enumerated(EnumType.STRING)
    private DrinkingEnum drinking;

    private String introduction;

    private String preference;

    private String nondepartment;

    private String nonstudentid;

    private String nonage;
    
    private String nonRegion;

    private String imgUrl;
    private int warning;

    @OneToMany(mappedBy = "member")
    private List<Matching> matchingList = new ArrayList<>();
    public Member() {
    }

    public static Member toMember(MemberRequestDTO.MemberJoinDTO request, PasswordEncoder passwordEncoder,
                                  String imgurl) {
        RoleEnum role = (request.getRole() == 0) ? RoleEnum.USER : RoleEnum.ADMIN;
        GenderEnum gender = (request.getGender() == 0) ? GenderEnum.FEMALE : GenderEnum.MALE;
        DistanceEnum distance = (request.getDistance() == 0) ? DistanceEnum.LONG : DistanceEnum.SHORT;
        SmokingEnum smoking = (request.getSmoking() == 0) ? SmokingEnum.SMOKER : SmokingEnum.NONSMOKER;
        DrinkingEnum drinking = (request.getDrinking() == 0) ? DrinkingEnum.DRINKER : DrinkingEnum.NONDRINKER;
        return Member.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .status(AccountStatusEnum.AUDIT)
                .role(role)
                .nickname(request.getNickname())
                .phone(request.getPhone())
                .birthday(request.getBirthday())
                .gender(gender)
                .height(request.getHeight())
                .region(request.getRegion())
                .department(request.getDepartment())
                .preference(request.getPreference())
                .distance(distance)
                .smoking(smoking)
                .drinking(drinking)
                .introduction(request.getIntroduction())
                .preference(request.getPreference())
                .nondepartment(request.getNondepartment())
                .nonstudentid(request.getNonstudentid())
                .nonage(request.getNonage())
                .nonRegion(request.getNonRegion())
                .imgUrl(imgurl)
                .warning(0)
                .build();
    }
}
