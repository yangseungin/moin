package com.moim.member.ui;

import com.moim.member.application.dto.SignupMemberRequest;
import com.moim.member.application.dto.SignupMemberResponse;
import com.moim.member.domain.Member;
import com.moim.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public SignupMemberResponse signup(SignupMemberRequest request) {
        Member member = request.toEntity();
        Member saved = memberRepository.save(member);
        return SignupMemberResponse.of(saved);
    }
}
