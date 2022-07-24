package com.moim.jwt.application;

import com.moim.jwt.application.dto.LoginRequest;
import com.moim.jwt.application.dto.TokenData;
import com.moim.jwt.utils.JwtUtil;
import com.moim.member.domain.Member;
import com.moim.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    public TokenData login(LoginRequest request) {
        Member member = memberRepository.findByUserId(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 회원입니다."));
        member.verificationPassword(request.getPassword());

        String encode = jwtUtil.getToken(request.getUserId());

        return new TokenData(encode);
    }
}
