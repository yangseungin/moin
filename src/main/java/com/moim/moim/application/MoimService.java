package com.moim.moim.application;

import com.moim.member.domain.Member;
import com.moim.member.domain.MemberRepository;
import com.moim.moim.application.dto.CreateMoimRequest;
import com.moim.moim.application.dto.CreateMoimResponse;
import com.moim.moim.application.dto.MoimResponse;
import com.moim.moim.domain.Moim;
import com.moim.moim.domain.MoimRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MoimService {
    private final MemberRepository memberRepository;

    private final MoimRepository moimRepository;

    @Transactional
    public CreateMoimResponse createMoim(CreateMoimRequest request, Authentication authentication) {
        if (moimRepository.existsByTitle(request.getTitle())) {
            throw new IllegalArgumentException("이미 존재하는 모임입니다.");
        }

        Member member = memberRepository.findByMemberId(authentication.getName()).orElseThrow();

        Moim save = moimRepository.save(request.toEntity(member));
        save.add(member);
        return CreateMoimResponse.of(save);


    }

    public List<MoimResponse> findMoims() {
        List<Moim> allMoim = moimRepository.findAll();
        return allMoim.stream()
                .map(MoimResponse::of)
                .collect(Collectors.toList());
    }

    public MoimResponse findMoim(String encodedMoimTitle) {
        Moim moim = findMoimByTitle(encodedMoimTitle);
        return MoimResponse.of(moim);
    }

    private Moim findMoimByTitle(String request) {
        return moimRepository.findByTitle(request)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 모임입니다."));
    }

    @Transactional
    public MoimResponse changeRecruit(String moimTitle, Authentication authentication) {
        Moim moim = findMoimByTitle(moimTitle);
        Member member = memberRepository.findByMemberId(authentication.getName()).orElseThrow();
        moim.isOwner(member);
        moim.changeRecruitState();
        return MoimResponse.of(moim);
    }

    @Transactional
    public MoimResponse closeMoim(String moimTitle, Authentication authentication) {
        Moim moim = findMoimByTitle(moimTitle);
        Member member = memberRepository.findByMemberId(authentication.getName()).orElseThrow();
        moim.isOwner(member);
        moim.moimClose();
        return MoimResponse.of(moim);
    }

    @Transactional
    public MoimResponse addMember(String moimTitle, Authentication authentication) {
        Moim moim = findMoimByTitle(moimTitle);
        Member member = memberRepository.findByMemberId(authentication.getName()).orElseThrow();
        moim.add(member);
        return MoimResponse.of(moim);
    }

    @Transactional
    public MoimResponse removeMember(String moimTitle, Authentication authentication) {
        Moim moim = findMoimByTitle(moimTitle);
        Member member = memberRepository.findByMemberId(authentication.getName()).orElseThrow();
        moim.remove(member);
        return MoimResponse.of(moim);
    }
}
