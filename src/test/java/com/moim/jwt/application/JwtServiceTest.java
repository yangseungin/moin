package com.moim.jwt.application;

import com.moim.jwt.application.dto.LoginRequest;
import com.moim.jwt.application.dto.TokenData;
import com.moim.jwt.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JwtServiceTest {
    private static final String SECRET = "12345678901234567890123456789012";
    private static final String VALID_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJ5YW5nc2kifQ.UZqyZg2IDHTHHz2H_G_jMu-6iMVOQQEcZvWW4Vm77r0";

    private JwtUtil jwtUtil;
    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil(SECRET);
        jwtService = new JwtService(jwtUtil);
    }

    @Test
    void login() {
        TokenData login = jwtService.login(new LoginRequest("yangsi", "password"));
        assertThat(login.getAccessToken()).contains(VALID_TOKEN);
    }

}
