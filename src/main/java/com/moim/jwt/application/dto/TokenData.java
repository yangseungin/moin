package com.moim.jwt.application.dto;

import lombok.Getter;

@Getter
public class TokenData {
    private String accessToken;

    public TokenData() {
    }

    public TokenData(String accessToken) {
        this.accessToken = accessToken;
    }
}
