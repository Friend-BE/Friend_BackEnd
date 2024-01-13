package com.friend.friend.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "member_id") // 외래키의 칼럼 이름
    private Member member;

    @ManyToOne
    @JoinColumn(name = "qa_id") // Qa 엔터티의 id를 참조
    private Qa qa;

    private long reportid;

}