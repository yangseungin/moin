package com.moim.moim.application.dto;

import com.moim.moim.domain.Moim;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateMoimResponse {
    private Long id;
    private String createBy;
    private String title;
    private String description;
    private Integer numberOfRecruit;
    private boolean recruiting;
    private boolean closed;
    private LocalDateTime recruitDeadline;
    private LocalDateTime createdDate;

    public CreateMoimResponse() {
    }

    public CreateMoimResponse(Long id, String createBy, String title, String description, Integer numberOfRecruit, boolean recruiting, boolean closed, LocalDateTime recruitDeadline, LocalDateTime createdDate) {
        this.id = id;
        this.createBy = createBy;
        this.title = title;
        this.description = description;
        this.numberOfRecruit = numberOfRecruit;
        this.recruiting = recruiting;
        this.closed = closed;
        this.recruitDeadline = recruitDeadline;
        this.createdDate = createdDate;
    }

    public static CreateMoimResponse of(Moim moim) {
        return new CreateMoimResponse(moim.getId(), moim.getCreateBy().getName(), moim.getTitle(), moim.getDescription(), moim.getNumberOfRecruit(), moim.isRecruiting(), moim.isClosed(), moim.getRecruitDeadline(), moim.getCreatedDate());
    }
}
