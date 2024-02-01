package com.friend.friend.domain;

import com.friend.friend.common.BaseEntity;
import com.friend.friend.domain.enums.AnswerStatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Matching extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="memberId")
    private Member member;

    private LocalDateTime date;

    private String opponent;

    private AnswerStatusEnum status;

    private String birthday; //상대방 생년월일

}
