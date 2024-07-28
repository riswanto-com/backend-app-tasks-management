package com.tasks.management.dto.notes;

import java.time.LocalDateTime;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotesSelect {
    private Long id;
    private boolean noteType;
    private String noteDescription;
    private String username;
    private String email;
    private LocalDateTime noteCreateDate;


    public NotesSelect(
        Long id,
        boolean noteType,
        String noteDescription,
        String username,
        String email,
        LocalDateTime noteCreateDate
        
    ){
        this.id=id;
        this.noteType=noteType;
        this.noteDescription=noteDescription;
        this.username=username;
        this.email=email;
        this.noteCreateDate=noteCreateDate;

    }
}
