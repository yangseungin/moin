package com.moim.member.application.dto;

import com.moim.member.domain.Member;
import lombok.Getter;

@Getter
public class SignupMemberResponse {

    private Long id;
    private String memberId;
    private String name;
    private String email;

    protected SignupMemberResponse(Long id, String memberId, String name, String email) {
        this.id = id;
        this.memberId = memberId;
        this.name = name;
        this.email = email;
    }

    public static SignupMemberResponse of(Member member) {
        return new SignupMemberResponse(member.getId(), member.getMemberId(), member.getName(), member.getEmail());
    }
}
