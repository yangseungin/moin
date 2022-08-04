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
        if(moimRepository.existsByTitle(request.getTitle())){
            throw new IllegalArgumentException("이미 존재하는 모임입니다.");
        }

        Member member = memberRepository.findByMemberId(authentication.getName()).orElseThrow();
        Moim moim = request.toEntity(member);
        Moim save = moimRepository.save(moim);
        return CreateMoimResponse.of(save);


    }

    public List<MoimResponse> findMoims() {
        List<Moim> allMoim = moimRepository.findAll();
        return allMoim.stream()
                .map(moim -> new MoimResponse(moim))
                .collect(Collectors.toList());
    }
}
