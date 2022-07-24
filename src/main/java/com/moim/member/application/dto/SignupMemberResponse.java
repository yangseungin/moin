package com.moim.member.application.dto;

import com.moim.member.domain.Member;
import lombok.Getter;

@Getter
public class SignupMemberResponse {

    private Long id;
    private String name;
    private String birthday;
    private String gender;
    private String memberId;
    private String password;
    private String email;
    private String affiliation; // 소속
    private String inedibleIngredients; // 먹을수없는 재료
    private String bio;

    public SignupMemberResponse(Long id, String name, String birthday, String gender, String memberId, String password, String email, String affiliation, String inedibleIngredients, String bio) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.memberId = memberId;
        this.password = password;
        this.email = email;
        this.affiliation = affiliation;
        this.inedibleIngredients = inedibleIngredients;
        this.bio = bio;
    }

    public static SignupMemberResponse of(Member member) {
        return new SignupMemberResponse(member.getId(), member.getName(), member.getBirthday(), member.getGender(), member.getMemberId(), member.getPassword(), member.getEmail(), member.getAffiliation(), member.getInedibleIngredients(), member.getBio());
    }
}
