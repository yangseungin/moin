package com.moim.member.application.dto;

import com.moim.member.domain.Member;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Getter
public class SignupMemberRequest {

    private String memberId;
    private String name;
    @Pattern(regexp = "^(19[0-9][0-9]|20\\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$")
    private String birth;
    private String gender;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{7,}$")
    private String password;
    @Email
    private String email;

    public SignupMemberRequest() {
    }

    public SignupMemberRequest(String memberId, String name, String birth, String gender, String password, String email) {
        this.memberId = memberId;
        this.name = name;
        this.birth = birth;
        this.gender = gender;
        this.password = password;
        this.email = email;
    }

    public Member toEntity() {
        return new Member(memberId, name, birth, gender, password, email);
    }

    public void encodePassword(String encodedPassword) {
        this.password = encodedPassword;
    }
}
