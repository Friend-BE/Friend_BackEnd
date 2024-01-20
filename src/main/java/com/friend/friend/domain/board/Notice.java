package com.friend.friend.domain.board;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("Notice")
@Getter @Setter
public class Notice extends Board {
    private String password;
}
