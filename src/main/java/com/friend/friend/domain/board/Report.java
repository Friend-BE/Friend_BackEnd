package com.friend.friend.domain.board;

import com.friend.friend.domain.enums.ReportStatusEnum;
import com.friend.friend.dto.ReportRequestDto;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    @Enumerated(EnumType.STRING)
    private ReportStatusEnum ReportStatus;
    public Report(ReportRequestDto request) {
        this.setTitle(request.getTitle());
        this.setBody(request.getBody());
        this.setAuthor(request.getAuthor());
        this.setBadMemberId(request.getBadMemberId());
        this.setBadMemberNickname(request.getBadMemberNickname());
        this.setReportStatus(ReportStatusEnum.INCOMPLETE);
    }
}
