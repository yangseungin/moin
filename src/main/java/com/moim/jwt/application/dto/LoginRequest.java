package com.moim.jwt.application.dto;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String memberId;
    private String password;

    public LoginRequest() {
    }

    public LoginRequest(String memberId, String password) {
        this.memberId = memberId;
        this.password = password;
    }
}
