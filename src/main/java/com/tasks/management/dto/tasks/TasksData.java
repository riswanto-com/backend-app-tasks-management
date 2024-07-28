package com.tasks.management.dto.tasks;

import java.time.LocalDateTime;

import com.tasks.management.models.Project;
import com.tasks.management.models.Status;
import com.tasks.management.models.User;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class TasksData {
     private Long id;
    private String projectName;
    private Integer projectCreateId;
    private LocalDateTime projectCreateDate;
    private String projectDescription;
    private LocalDateTime projectEndDate;
    private Project projects;
    private Status status;
    private User users;
}
