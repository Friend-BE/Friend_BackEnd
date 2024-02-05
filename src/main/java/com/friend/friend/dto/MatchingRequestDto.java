package com.friend.friend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.friend.friend.domain.enums.MatchingStatusEnum;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class MatchingRequestDto {
    private final String opponent = null;

    private final MatchingStatusEnum statusEnum = MatchingStatusEnum.INCOMPLETE;

}
