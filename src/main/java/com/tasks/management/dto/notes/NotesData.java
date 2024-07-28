package com.tasks.management.dto.notes;

import com.tasks.management.models.Project;
import com.tasks.management.models.Tasks;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class NotesData {

    private Long id;
    private String noteDescription;
    private Tasks tasks;
    private Project projects;

}
