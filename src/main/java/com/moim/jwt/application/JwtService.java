package com.moim.jwt.application;

import com.moim.jwt.application.dto.LoginRequest;
import com.moim.jwt.application.dto.TokenData;
import com.moim.jwt.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtUtil jwtUtil;

    public TokenData login(LoginRequest request) {
        String encode = jwtUtil.getToken(request.getUserId());
        Claims payload = jwtUtil.getPayload(encode);
        //TODO 확인용 추후제거
        System.out.println(payload.get("userId"));

        return new TokenData(encode);
    }
}
