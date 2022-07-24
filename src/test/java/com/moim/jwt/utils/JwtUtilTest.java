package com.moim.jwt.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.SignatureException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class JwtUtilTest {

    private static final String secret = "12345678901234567890123456789012";
    private static final String VALID_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJ5YW5nc2kifQ.UZqyZg2IDHTHHz2H_G_jMu-6iMVOQQEcZvWW4Vm77r0";
    private static final String INVALID_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJ5YW5nc2kifQ.UZqyZg2IDHTHHz2H_G_jMu-6iMVOQQEcZvWW4Verror";
    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil(secret);
    }

    @Test
    void encode() {
        String token = jwtUtil.getToken("yangsi");

        assertThat(token).isEqualTo(VALID_TOKEN);
    }

    @Test
    void decode() {
        Claims claims = jwtUtil.getPayload(VALID_TOKEN);

        assertThat(claims.get("userId")).isEqualTo("yangsi");
    }

    @Test
    void decodeWithInvalidToken() {
        assertThatThrownBy(() -> jwtUtil.getPayload(INVALID_TOKEN))
                .isInstanceOf(SignatureException.class);
    }

    @Test
    void validToken() {
        String invalidToken = "abc.def.ghi";
        String yangsi = jwtUtil.getToken("yangsi");
        assertThat(jwtUtil.validToken(yangsi)).isTrue();
        assertThat(jwtUtil.validToken(invalidToken)).isFalse();
    }
}
