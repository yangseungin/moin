package com.moim.member.domain;

import com.moim.member.application.dto.UpdateMemberRequest;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String birthday;
    private String gender;
    @Column(unique = true)
    private String memberId;
    private String password;
    @Column(unique = true)
    private String email;
    private String affiliation; // 소속
    private String inedibleIngredients; // 먹을수없는 재료
    private String bio;


    public Member(String memberId, String name, String birthday, String gender, String password, String email) {
        this(null, name, birthday, gender, memberId, password, email, "", "", "");
    }

    public Member(Long id, String name, String birthday, String gender, String memberId, String password, String email, String affiliation, String inedibleIngredients, String bio) {
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

    public void updateInfomation(UpdateMemberRequest request) {
        this.name = request.getName();
        this.birthday = request.getBirth();
        this.gender = request.getGender();
        this.password = request.getPassword();
        this.email = request.getEmail();
    }
}
