package com.friend.friend.domain;

import com.friend.friend.common.BaseEntity;
import com.friend.friend.domain.enums.GenderEnum;
import com.friend.friend.domain.enums.MatchingStatusEnum;
import com.friend.friend.dto.MatchingRequestDto;
import jakarta.persistence.*;
import javax.annotation.Nullable;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Matching extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    private GenderEnum gender;

    private String name;

    @Column(nullable = true)
    private String opponent;

    private MatchingStatusEnum status;

    private LocalDateTime date;

    @Column(nullable = true)
    private String birthday; //상대방 생년월일

    public Matching(MatchingRequestDto requestDto) {
        this.setId(requestDto.getId());
    }
}
