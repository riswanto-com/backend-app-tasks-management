package com.tasks.management.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "note")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "note_type")
    private boolean noteType;
    @Column(name = "note_description",length = 1000)
    private  String noteDescription;
    @ManyToOne
    @JoinColumn(name = "note_create_id")
    private User users;
    @Column(name = "note_create_date")
    private LocalDateTime noteCreateDate;
    @ManyToOne
    @JoinColumn(name = "note_tasks_id")
    private Tasks tasks;
    @ManyToOne
    @JoinColumn(name = "note_project_id")
    private Project projects;
    @Column(name = "note_active")
    private boolean noteActive=true;

}
