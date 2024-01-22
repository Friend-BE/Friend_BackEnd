package com.friend.friend.domain.board;

import com.friend.friend.dto.NoticeRequestDto;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("Notice")
@Getter
@NoArgsConstructor
public class Notice extends Board {

    public Notice(NoticeRequestDto requestDto) {
        this.setTitle(requestDto.getTitle());
        this.setBody(requestDto.getBody());
        this.setAuthor(requestDto.getAuthor());
    }

    public void update(NoticeRequestDto requestDto) {
        this.setTitle(requestDto.getTitle());
        this.setBody(requestDto.getBody());
    }
}
