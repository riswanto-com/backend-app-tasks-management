package com.tasks.management.dto.project;

import com.tasks.management.models.Status;
import com.tasks.management.models.User;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class ProjectData {
    private Long id;
    @NotEmpty(message = "project name is reuired")
    private String projectName;
    private Integer projectCreateId;
    private LocalDateTime projectCreateDate;
    private String projectDescription;
    private LocalDateTime projectEndDate;
    private Status status;
    private User users;
}
