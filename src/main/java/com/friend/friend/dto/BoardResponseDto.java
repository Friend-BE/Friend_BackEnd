package com.friend.friend.dto;

import com.friend.friend.domain.board.Board;
import java.time.LocalDateTime;

import com.friend.friend.domain.board.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponseDto {
    private Long id;
    private String title;
    private String body;
    private String author;
    private Long views;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BoardResponseDto(Review board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.body = board.getBody();
        this.author = board.getAuthor();
        this.createdAt = board.getCreatedAt();
        this.updatedAt = board.getUpdatedAt();
        this.views=board.getViews();
    }
    public static BoardResponseDto convertBoardResponseDTO(Review board) {
        return new BoardResponseDto(board.getId(), board.getTitle(), board.getBody(), board.getAuthor(),board.getViews(), board.getCreatedAt(), board.getUpdatedAt());
    }
}
