package com.friend.friend.dto;

import com.friend.friend.domain.enums.AnswerStatusEnum;
import com.friend.friend.domain.enums.PrivacyEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class QaRequestDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class writeQaDTO{
        private String title;   //제목
        private String body; // 본문
        private String author;    //작성자
        private PrivacyEnum privacy;
        private String password; //비밀번호 - 있어야되지않나?
    }
}
