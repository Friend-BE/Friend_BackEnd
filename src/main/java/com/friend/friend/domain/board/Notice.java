package com.friend.friend.domain;

import com.friend.friend.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Notice extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "notice_id")
    private String id;

    private String author; // 작성자
    private String title;
    private String password;
    private String body;
}
