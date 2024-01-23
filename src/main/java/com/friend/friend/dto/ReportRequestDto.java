package com.friend.friend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReportRequestDto {
    private String title;
    private String body;
    private String author;
    private Long badMemberId;
}
