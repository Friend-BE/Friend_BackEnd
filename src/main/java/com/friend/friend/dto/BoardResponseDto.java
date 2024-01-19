package com.friend.friend.dto;

import com.friend.friend.domain.board.Board;
import java.time.LocalDateTime;

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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.body = board.getBody();
        this.author = board.getAuthor();
        this.createdAt = board.getCreatedAt();
        this.updatedAt = board.getUpdatedAt();
    }
    public static BoardResponseDto convertBoardResponseDTO(Board board) {
        return new BoardResponseDto(board.getId(), board.getTitle(), board.getBody(), board.getAuthor(), board.getCreatedAt(), board.getUpdatedAt());
    }
}
