package com.moim.member.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
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


}
