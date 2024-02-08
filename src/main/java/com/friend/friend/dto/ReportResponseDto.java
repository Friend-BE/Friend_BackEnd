package com.friend.friend.dto;

import com.friend.friend.domain.board.Report;
import com.friend.friend.domain.enums.ReportStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReportResponseDto {
    private Long id;
    private String title;
    private String body;
    private String author;
    private Long badMemberId;
    private String badMemberNickname;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private ReportStatusEnum status;


    public ReportResponseDto(Report report){
        this.id = report.getId();
        this.title = report.getTitle();
        this.body = report.getBody();
        this.author = report.getAuthor();
        this.badMemberId = report.getBadMemberId();
        this.badMemberNickname=report.getBadMemberNickname();
        this.createdAt = report.getCreatedAt();
        this.updatedAt = report.getUpdatedAt();
        this.status = report.getReportStatus();
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class completedReportDto{
        private Long id;
        private ReportStatusEnum status;
    }
}
