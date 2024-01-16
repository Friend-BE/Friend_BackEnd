package com.friend.friend.controller.DTO;

import com.friend.friend.domain.enums.DistanceEnum;
import com.friend.friend.domain.enums.DrinkingEnum;
import com.friend.friend.domain.enums.SmokingEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class profileDTO{
        String nickname;
        String birthday;
        double height;
        String region;
        DistanceEnum distance;
        SmokingEnum smoking;
        DrinkingEnum drinking;
        String department;
        String introduction;

    }
}
