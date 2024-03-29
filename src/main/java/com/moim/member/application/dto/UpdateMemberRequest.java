package com.moim.member.application.dto;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Getter
public class UpdateMemberRequest {
    private String name;
    @Pattern(regexp = "^(19[0-9][0-9]|20\\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$")
    private String birth;
    private String gender;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{7,}$")
    private String password;
    @Email
    private String email;

    public UpdateMemberRequest() {
    }

    public UpdateMemberRequest(String name, String birth, String gender, String password, String email) {
        this.name = name;
        this.birth = birth;
        this.gender = gender;
        this.password = password;
        this.email = email;
    }
}
