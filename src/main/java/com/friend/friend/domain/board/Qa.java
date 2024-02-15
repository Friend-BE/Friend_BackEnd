package com.friend.friend.domain.board;

import com.friend.friend.domain.enums.AnswerStatusEnum;
import com.friend.friend.domain.enums.PrivacyEnum;
import com.friend.friend.dto.QaRequestDTO;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("QA")
@Getter
@Setter
public class Qa extends Board {
    @Enumerated(EnumType.STRING)
    private AnswerStatusEnum status;
    @Enumerated(EnumType.STRING)
    private PrivacyEnum privacy;
    private String answer;
    private String password;

    public void update(QaRequestDTO.updateQaDTO updateQaDTO){
        this.setTitle(updateQaDTO.getTitle());
        this.setBody(updateQaDTO.getBody());
        this.setPrivacy(updateQaDTO.getPrivacy());
        this.setPassword(updateQaDTO.getPassword());
    }
}
