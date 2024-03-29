package com.friend.friend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchListByDateDTO {
    private Long id;
    private String manNickname;
    private String manPhone;
    private String womanNickname;
    private String womanPhone;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
    private LocalDateTime matchDate;
}
