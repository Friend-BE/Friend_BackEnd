package com.friend.friend.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class MemberDenyResponseDTO {
    private Long id;
    private Boolean result;
}
