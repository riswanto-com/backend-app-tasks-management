package com.tasks.management.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tasks.management.models.Note;

public interface NotesRepos extends JpaRepository<Note,Long>{
    @Query("SELECT new com.tasks.management.dto.notes.NotesSelect("+
    "n.id, n.noteType,n.noteDescription,u.username,u.email,n.noteCreateDate"+
    ") FROM Note n JOIN n.users u JOIN n.projects p WHERE " + 
    "p.id =:id AND n.noteActive =true ORDER BY n.noteCreateDate DESC")
    public Page<Note> findByListNotes(@Param("id") Long id,Pageable pageable);
    @Query("SELECT new com.tasks.management.dto.notes.NotesSelect("+
    "n.id, n.noteType,n.noteDescription,u.username,u.email,n.noteCreateDate"+
    ") FROM Note n JOIN n.users u JOIN n.tasks p WHERE " + 
    "p.id =:id AND n.noteActive =true ORDER BY n.noteCreateDate DESC")
    public Page<Note> findByListNotesTasks(@Param("id") Long id,Pageable pageable);
}
