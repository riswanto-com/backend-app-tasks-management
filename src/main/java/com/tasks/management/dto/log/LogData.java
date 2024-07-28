package com.tasks.management.dto.log;

import java.time.LocalDateTime;

import com.tasks.management.models.Project;
import com.tasks.management.models.Tasks;
import com.tasks.management.models.User;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter

public class LogData {
    private String logAction;
    private String logDescription;
    private LocalDateTime logCreateDate;
    private Project projects;
    private Tasks tasks;
    private User users;
}
