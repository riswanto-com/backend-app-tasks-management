package com.tasks.management.dto.project;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectSelect {

    private Long id;
    private String projectName;
    private Integer projectCreateId;
    private LocalDateTime projectCreateDate;
    private String projectDescription;
    private LocalDateTime projectEndDate;
    private String status;
    private String username;
    private String email;
    

    public ProjectSelect(Long id,
            String projectName,
            Integer projectCreateId,
            LocalDateTime projectCreateDate,
            String projectDescription,
            LocalDateTime projectEndDate,
            String status,
            String username,
            String email
            ) {
        this.id = id;
        this.projectName = projectName;
        this.projectCreateId = projectCreateId;
        this.projectCreateDate = projectCreateDate;
        this.projectDescription = projectDescription;
        this.projectEndDate = projectEndDate;
        this.status = status;
        this.username = username;
        this.email=email;
    }

}
