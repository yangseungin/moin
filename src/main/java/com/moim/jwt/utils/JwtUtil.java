package com.moim.jwt.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Objects;

@Component
public class JwtUtil {
    private static final String CLAIM_NAME = "memberId";
    private final Key key;

    public JwtUtil(@Value("${jwt.secret}") String secret) {
        key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String getToken(String memberId) {
        return Jwts.builder()
                .claim(CLAIM_NAME, memberId)
                .signWith(key)
                .compact();
    }

    public Claims getPayload(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public String getMemberId(String token){
        return getPayload(token).get("memberId")
                .toString();
    }

    public boolean validToken(String token) {
        if (Objects.isNull(token) || token.isBlank()) {
            return false;
        }

        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            return !Objects.isNull(claimsJws.getBody().get(CLAIM_NAME));
        } catch (Exception e) {
            return false;
        }
    }
}
