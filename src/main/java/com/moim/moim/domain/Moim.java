package com.moim.moim.domain;

import com.moim.member.domain.Member;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
@EntityListeners(AuditingEntityListener.class)
public class Moim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member createBy;
    @ManyToMany
    private Set<Member> members  = new HashSet<>();

    @Column(unique = true)
    private String title;
    private String description;
    private Integer numberOfRecruit;
    private boolean recruiting;
    private boolean closed;
    private LocalDateTime recruitDeadline;
    @CreatedDate
    private LocalDateTime createdDate;

    public Moim(Member createBy, String title, String description, Integer numberOfRecruit, LocalDateTime recruitDeadline) {
        this.createBy = createBy;
        this.title = title;
        this.description = description;
        this.numberOfRecruit = numberOfRecruit;
        this.recruitDeadline = recruitDeadline;
    }
    public Moim(Long id, Member createBy, Set<Member> members, String title, String description, Integer numberOfRecruit, boolean recruiting, boolean closed, LocalDateTime recruitDeadline, LocalDateTime createdDate) {
        this.id = id;
        this.createBy = createBy;
        this.members = members;
        this.title = title;
        this.description = description;
        this.numberOfRecruit = numberOfRecruit;
        this.recruiting = recruiting;
        this.closed = closed;
        this.recruitDeadline = recruitDeadline;
        this.createdDate = createdDate;
    }
}
