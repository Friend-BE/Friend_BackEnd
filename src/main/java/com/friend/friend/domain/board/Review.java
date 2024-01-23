package com.friend.friend.domain.board;


import com.friend.friend.dto.ReviewRequestsDto;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("review")
@Getter @Setter
public class Review extends Board {
    private Long views;
    public Review(ReviewRequestsDto reviewRequestsDto){
        this.setTitle(reviewRequestsDto.getTitle());
        this.setBody((reviewRequestsDto.getBody()));
        this.setAuthor(reviewRequestsDto.getAuthor());
        this.views=0L;
    }
    public void update(ReviewRequestsDto reviewRequestsDto){
        this.setTitle(reviewRequestsDto.getTitle());
        this.setBody(reviewRequestsDto.getBody());
    }
    public Review() {

    }
}
