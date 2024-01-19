package com.friend.friend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardRequestsDto {
    private String title;
    private String body;
    private String author;
    private String password;

    public BoardRequestsDto(String title, String body, String author){
        this.title=title;
        this.body=body;
        this.author=author;
    }
    public BoardRequestsDto(String title, String body, String author,String password){
        this.title=title;
        this.body=body;
        this.author=author;
        this.password=password;
    }
}
