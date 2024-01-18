package com.friend.friend.domain.board;

import com.friend.friend.common.BaseEntity;
import com.friend.friend.domain.enums.AnswerStatusEnum;
import com.friend.friend.domain.enums.PrivacyEnum;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("QA")
@Getter
@Setter
public class Qa extends Post {
    private AnswerStatusEnum status;
    private PrivacyEnum privacy;
    private String answer;
    private String password;
}
