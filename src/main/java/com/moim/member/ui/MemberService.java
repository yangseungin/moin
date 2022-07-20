package com.moim.member.ui;

import com.moim.member.application.dto.SignupMemberRequest;
import com.moim.member.application.dto.SignupMemberResponse;
import com.moim.member.domain.Member;
import com.moim.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public SignupMemberResponse signup(SignupMemberRequest request) {
        request.encodePassword(passwordEncoder.encode(request.getPassword()));
        //TODO 추후에 가입여부 확인이 필요

        Member member = request.toEntity();
        Member saved = memberRepository.save(member);
        return SignupMemberResponse.of(saved);
    }
}
