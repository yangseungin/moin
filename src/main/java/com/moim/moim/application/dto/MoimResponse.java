package com.moim.moim.application.dto;

import com.moim.moim.domain.Moim;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MoimResponse {
    private String title;
    private String description;
    private String createBy;
    private Integer numberOfRecruit;
    private boolean recruiting;
    private boolean closed;
    private LocalDateTime recruitDeadline;
    private LocalDateTime createdDate;

    public MoimResponse() {
    }

    public MoimResponse(String title, String description, String createBy, Integer numberOfRecruit, boolean recruiting, boolean closed, LocalDateTime recruitDeadline, LocalDateTime createdDate) {
        this.title = title;
        this.description = description;
        this.createBy = createBy;
        this.numberOfRecruit = numberOfRecruit;
        this.recruiting = recruiting;
        this.closed = closed;
        this.recruitDeadline = recruitDeadline;
        this.createdDate = createdDate;
    }

    public MoimResponse(Moim moim) {
        this(moim.getTitle(), moim.getDescription(), moim.getCreateBy().getName(), moim.getNumberOfRecruit(), moim.isRecruiting(), moim.isClosed(), moim.getRecruitDeadline(), moim.getCreatedDate());
    }

    public static MoimResponse of(Moim moim) {
        return new MoimResponse(moim);
    }
}
