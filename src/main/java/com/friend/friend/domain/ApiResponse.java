package com.friend.friend.domain;

import com.friend.friend.domain.enums.HTTPResponseEnum;
import lombok.Data;

@Data
public class ApiResponse {

    private HTTPResponseEnum status;
    private String message;

    public ApiResponse() {
        this.status = HTTPResponseEnum.BAD_REQUEST;
        this.message = null;
    }
}
