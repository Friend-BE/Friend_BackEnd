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
        private AnswerStatusEnum status; //답변여부
        private PrivacyEnum privacy;
        private LocalDateTime updatedAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class getQaDTO {
        private Long id;
        private String title;   //제목
        private String body;     //본문
        private LocalDateTime updatedAt; //작성시간
        private String author;    //작성자
        private AnswerStatusEnum status; //답변여부
    }

}
