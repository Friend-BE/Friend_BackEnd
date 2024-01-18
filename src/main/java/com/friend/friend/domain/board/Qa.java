package com.friend.friend.domain.board;

import com.friend.friend.common.BaseEntity;
import com.friend.friend.domain.enums.AnswerStatusEnum;
import com.friend.friend.domain.enums.PrivacyEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Qa extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String body;
    private AnswerStatusEnum status;
    private PrivacyEnum privacy;
    private String answer;
}
