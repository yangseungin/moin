package com.moim.member.application.dto;

import com.moim.member.domain.Member;
import lombok.Getter;

@Getter
public class SignupMemberRequest {

    private String userId;
    private String name;
    private String birth;
    private String gender;
    private String password;
    private String email;


    public Member toEntity() {
        return new Member(userId, name, birth, gender, password, email);
    }
}
