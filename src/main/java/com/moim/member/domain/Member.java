package com.moim.member.domain;

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
    private String userId;
    private String password;
    @Column(unique = true)
    private String email;
    private String affiliation; // 소속
    private String inedibleIngredients; // 먹을수없는 재료
    private String bio;


    public Member(String userId, String name, String birthday, String gender, String password, String email) {
        this(null, name, birthday, gender, userId, password, email, null, null, null);
    }

    public Member(Long id, String name, String birthday, String gender, String userId, String password, String email, String affiliation, String inedibleIngredients, String bio) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.affiliation = affiliation;
        this.inedibleIngredients = inedibleIngredients;
        this.bio = bio;
    }
}
