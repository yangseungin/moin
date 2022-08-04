package com.moim.jwt.application;

import com.moim.jwt.application.dto.LoginRequest;
import com.moim.jwt.application.dto.TokenData;
import com.moim.jwt.utils.JwtUtil;
import com.moim.member.application.error.AuthorizationException;
import com.moim.member.domain.Member;
import com.moim.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public TokenData login(LoginRequest request) {
        Member member = memberRepository.findByMemberId(request.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 회원입니다."));
        verificationPassword(request, member);

        String encode = jwtUtil.getToken(request.getMemberId());

        return new TokenData(encode);
    }

    private void verificationPassword(LoginRequest request, Member member) {
        if(!passwordEncoder.matches(request.getPassword(), member.getPassword())){
            throw new AuthorizationException("패스워드가 일치하지 않습니다.");
        }
    }
}
