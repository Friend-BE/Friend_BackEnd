package com.friend.friend.dto;

import lombok.Getter;

@Getter
public class SuccessResponseDto {
    //성공여부 담을 dto
    private boolean success;

    public SuccessResponseDto(boolean success) {
        this.success = success;
    }
}
