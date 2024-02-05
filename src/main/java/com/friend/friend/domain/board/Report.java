package com.friend.friend.domain.board;

import com.friend.friend.domain.enums.AnswerStatusEnum;
import com.friend.friend.dto.ReportRequestDto;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("Report")
@Getter
@Setter
@NoArgsConstructor
public class Report extends Board {
    private Long badMemberId;
    private String badMemberNickname;
    public Report(ReportRequestDto request) {
        this.setTitle(request.getTitle());
        this.setBody(request.getBody());
        this.setAuthor(request.getAuthor());
        this.setBadMemberId(request.getBadMemberId());
        this.setBadMemberNickname(request.getBadMemberNickname());
    }
}
