package com.moim.member.application.dto;

import com.moim.member.domain.Member;
import lombok.Getter;

@Getter
public class MemberResponse {

    private Long id;
    private String memberId;
    private String name;
    private String birth;
    private String gender;
    private String password;
    private String email;
    private String affiliation; // 소속
    private String inedibleIngredients; // 먹을수없는 재료
    private String bio;

    protected MemberResponse(Long id, String memberId, String name, String birth, String gender, String password, String email, String affiliation, String inedibleIngredients, String bio) {
        this.id = id;
        this.memberId = memberId;
        this.name = name;
        this.birth = birth;
        this.gender = gender;
        this.password = password;
        this.email = email;
        this.affiliation = affiliation;
        this.inedibleIngredients = inedibleIngredients;
        this.bio = bio;
    }

    public static MemberResponse of(Member member) {
        return new MemberResponse(member.getId(), member.getMemberId(), member.getName(), member.getBirthday(), member.getGender(), member.getPassword(), member.getEmail(), member.getAffiliation(), member.getInedibleIngredients(), member.getBio());
    }
}
