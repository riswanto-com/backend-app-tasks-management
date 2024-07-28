package com.tasks.management.dto.project;


import com.tasks.management.models.Status;
import com.tasks.management.models.User;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class ProjectAction {
    private Long id;
    private LocalDateTime projectEndDate;
    private Status status;
    @NotEmpty(message = "type action is required")
    private String typeAction;
    private User users;
}
    

