package com.friend.friend.dto;

import com.friend.friend.domain.board.Board;
import com.friend.friend.domain.board.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReviewResponseDto {
    private Long id;
    private String title;
    private String body;
    private String author;
    private Long views;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    public ReviewResponseDto(Review entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.body = entity.getBody();
        this.author = entity.getAuthor();
        this.views=entity.getViews();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
    }

    public ReviewResponseDto(Board board) {
    }
}
