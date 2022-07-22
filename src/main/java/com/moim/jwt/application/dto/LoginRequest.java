package com.moim.jwt.application.dto;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String userId;
    private String password;

    public LoginRequest() {
    }

    public LoginRequest(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
