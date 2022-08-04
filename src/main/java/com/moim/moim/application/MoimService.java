package com.moim.moim.application;

import com.moim.member.domain.Member;
import com.moim.member.domain.MemberRepository;
import com.moim.moim.application.dto.CreateMoimRequest;
import com.moim.moim.application.dto.CreateMoimResponse;
import com.moim.moim.domain.Moim;
import com.moim.moim.domain.MoimRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MoimService {
    private final MemberRepository memberRepository;

    private final MoimRepository moimRepository;

    @Transactional
    public CreateMoimResponse createMoim(CreateMoimRequest request) {
        //TODO 모임 존재여부 확인
        //TODO 회원인증로직을 우선 구현필요
        Member member = memberRepository.findByMemberId("yangsi").orElseThrow();
        Moim moim = request.toEntity(member);
        Moim save = moimRepository.save(moim);
        return CreateMoimResponse.of(save);


    }
}
