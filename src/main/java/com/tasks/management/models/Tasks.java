package com.tasks.management.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import com.fasterxml.jackson.annotation.JsonBackReference;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tasks_id")
    private Long id;
    @Column(name = "tasks_name")
    private String tasksName;
    @Column(name = "tasks_create_id")
    private Integer tasksCreateId;
    @Column(name = "tasks_create_date")
    private LocalDateTime tasksCreateDate;
    @Column(name = "tasks_description")
    @Lob
    private String tasksDescription;
    @Column(name = "tasks_end_date")
    private LocalDateTime tasksEndDate;
    @ManyToOne
    @JoinColumn(name = "tasks_project_id")
    @JsonBackReference
    private Project projects;
    @ManyToOne
    @JoinColumn(name = "tasks_status_id")
    // @JsonBackReference
    private Status status;
    @ManyToOne
    @JoinColumn(name = "tasks_user_id")
    // @JsonBackReference
    private User users;
    @Column(name = "tasks_active")
    private boolean tasksActive=true;
}
