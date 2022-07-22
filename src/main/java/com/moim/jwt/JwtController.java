package com.moim.jwt;

import com.moim.jwt.application.JwtService;
import com.moim.jwt.application.dto.LoginRequest;
import com.moim.jwt.application.dto.TokenData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
@RequiredArgsConstructor
public class JwtController {
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest request) {
        TokenData tokenData = jwtService.login(request);
        return ResponseEntity.ok().body(tokenData);
    }
}
