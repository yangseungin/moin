package com.moim.member.application.dto;

import com.moim.member.domain.Member;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Getter
public class SignupMemberRequest {

    private String userId;
    private String name;
    private String birth;
    private String gender;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{7,}$")
    private String password;
    @Email
    private String email;


    public Member toEntity() {
        return new Member(userId, name, birth, gender, password, email);
    }

    public void encodePassword(String encodedPassword) {
        this.password = encodedPassword;
    }
}
