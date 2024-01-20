package com.friend.friend.domain.board;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("review")
@Getter @Setter
public class Review extends Board {
    private Long views;
}
