package com.friend.friend.dto;

import com.friend.friend.domain.enums.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


public class QAResponseDTO {



    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class getQasDTO {
        private Long id;
        private String title;   //제목
        private String author;    //작성자
        private AnswerStatusEnum status;
        private PrivacyEnum privacy;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class getQaDTO {
        private Long id;
        private String title;   //제목
        private String body;     //본문
        private LocalDateTime createdAt; //작성시간
        private String author;    //작성자
    }

}
