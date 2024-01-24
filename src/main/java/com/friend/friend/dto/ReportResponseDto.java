package com.friend.friend.dto;

import com.friend.friend.domain.board.Report;
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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ReportResponseDto(Report report){
        this.id = report.getId();
        this.title = report.getTitle();
        this.body = report.getBody();
        this.author = report.getAuthor();
        this.badMemberId = report.getBadMemberId();
        this.createdAt = report.getCreatedAt();
        this.updatedAt = report.getUpdatedAt();
    }
}
