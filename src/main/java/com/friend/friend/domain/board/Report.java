package com.friend.friend.domain.board;

import com.friend.friend.dto.BoardRequestsDto;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("Report")
@Getter @Setter
public class Report extends Board{
    private Long badMemberId;
}
