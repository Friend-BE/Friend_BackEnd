package com.friend.friend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDetailDTO {
    private MemberResponseDTO.profileDTO profile;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
    private LocalDateTime matchDate;
    private int matchCount;
    private int matchCompleteCount;
    private int warningCount;
}
