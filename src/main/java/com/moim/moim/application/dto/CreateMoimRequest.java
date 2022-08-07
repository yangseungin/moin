package com.moim.moim.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.moim.member.domain.Member;
import com.moim.moim.domain.Moim;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
public class CreateMoimRequest {

    @NotBlank
    @Length(max = 30)
    private String title;
    @NotBlank
    @Length(max = 100)
    private String description;
    private Integer numberOfRecruit;
    private boolean recruiting;
    private boolean closed;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime recruitDeadline;

    public CreateMoimRequest() {
    }

    public CreateMoimRequest(String title, String description, Integer numberOfRecruit, boolean recruiting, boolean closed, LocalDateTime recruitDeadline) {
        this.title = title;
        this.description = description;
        this.numberOfRecruit = numberOfRecruit;
        this.recruiting = recruiting;
        this.closed = closed;
        this.recruitDeadline = recruitDeadline;
    }

    public Moim toEntity(Member member) {
        return new Moim(member, title, description, numberOfRecruit, recruitDeadline, recruiting, closed);
    }
}
