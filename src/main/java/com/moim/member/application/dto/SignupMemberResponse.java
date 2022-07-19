package com.moim.member.application.dto;

import com.moim.member.domain.Member;
import lombok.Getter;

@Getter
public class SignupMemberResponse {

    private Long id;
    private String userId;
    private String name;
    private String email;

    protected SignupMemberResponse(Long id, String userId, String name, String email) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public static SignupMemberResponse of(Member member) {
        return new SignupMemberResponse(member.getId(), member.getUserId(), member.getName(), member.getEmail());
    }
}
