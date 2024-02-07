package com.friend.friend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.friend.friend.domain.enums.MatchingStatusEnum;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MatchingRequestDto {
    private Long id;
//    private final String opponent = null;
//    private final MatchingStatusEnum statusEnum = MatchingStatusEnum.INCOMPLETE;

}
