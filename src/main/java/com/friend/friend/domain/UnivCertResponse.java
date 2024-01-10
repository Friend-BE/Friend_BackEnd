package com.friend.friend.domain;

import lombok.Data;

@Data
public class UnivCertResponse {

    private StatusEnum status;
    private String message;

    public UnivCertResponse() {
        this.status = StatusEnum.BAD_REQUEST;
        this.message = null;
    }
}
