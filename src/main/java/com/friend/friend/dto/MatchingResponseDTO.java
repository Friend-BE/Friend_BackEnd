package com.friend.friend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.friend.friend.domain.enums.AnswerStatusEnum;
import com.friend.friend.domain.enums.MatchingStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MatchingResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class getMatchingDTO {

        private Long id;

        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
        private LocalDateTime date;   //날짜

        private String opponent;    //상대방
        private MatchingStatusEnum status; //매칭완료여부
        private String bithday; //상대방 생년월일
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class getReportListDTO {
        private Long id;
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
        private LocalDateTime date;   //날짜
        private String opponentNickname;    //상대방
        private String opponentBirthday; //상대방 생년월일
        private String opponentDepartment;  //상대방 대학
    }

}
