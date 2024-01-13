package com.friend.friend.domain;

import com.friend.friend.common.BaseEntity;
import com.friend.friend.domain.enums.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;
    private String password;
    private AccountStatusEnum status;
    private RoleEnum role;
    private String nickname;
    private String phone;
    private String birthday;
    private GenderEnum gender;
    private double height;
    private String region;
    private String department;
    private DistanceEnum distance;
    private SmokingEnum smoking;
    private DrinkingEnum drinking;
    private String introduction;
    private String preference;
    private String nondepartment;
    private String nonstudentid;
    private String nonage;
    private String image;
}
