package com.friend.friend.dto;

import com.friend.friend.domain.board.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardRequestsDto {
    private String title;
    private String body;
    private String author;
    private String password;

    public BoardRequestsDto(Review board){
        this.title=board.getTitle();
        this.body=board.getBody();
        this.author=board.getAuthor();
    }
}
