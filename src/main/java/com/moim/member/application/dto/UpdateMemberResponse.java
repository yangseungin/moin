package com.moim.member.application.dto;

import com.moim.member.domain.Member;
import lombok.Getter;

@Getter
public class UpdateMemberResponse {

    private Long id;
    private String memberId;
    private String name;
    private String birth;
    private String gender;
    private String password;
    private String email;

    protected UpdateMemberResponse(Long id, String memberId, String name, String birth, String gender, String password, String email) {
        this.id = id;
        this.memberId = memberId;
        this.name = name;
        this.birth = birth;
        this.gender = gender;
        this.password = password;
        this.email = email;
    }

    public static UpdateMemberResponse of(Member member) {
        return new UpdateMemberResponse(member.getId(), member.getMemberId(), member.getName(), member.getBirthday(), member.getGender(), member.getPassword(), member.getEmail());
    }
}
