package com.moim.member.application;

import com.moim.member.application.dto.MemberResponse;
import com.moim.member.application.dto.SignupMemberRequest;
import com.moim.member.application.dto.SignupMemberResponse;
import com.moim.member.application.dto.UpdateMemberRequest;
import com.moim.member.application.dto.UpdateMemberResponse;
import com.moim.member.domain.Member;
import com.moim.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
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

    @Transactional
    public UpdateMemberResponse updateInfo(UpdateMemberRequest request, Authentication authentication) {
        Member member = getMember(authentication.getName());
        member.updateInfomation(request);

        return UpdateMemberResponse.of(member);
    }

    private Member getMember(String memberId) {
        return memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 회원입니다."));
    }

    public MemberResponse getInfo(Authentication authentication) {
        return MemberResponse.of(getMember(authentication.getName()));
    }
}
