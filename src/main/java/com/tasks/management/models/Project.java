package com.tasks.management.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.time.LocalDateTime;

@Entity
@Table(name = "project")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long id;
    @Column(name = "project_name")
    private String projectName;
    @Column(name = "project_create_id")
    private Integer projectCreateId;
    @Column(name = "project_create_date")
    private LocalDateTime projectCreateDate;
    @Column(name = "project_description")
    @Lob
    private String projectDescription;
    @Column(name = "project_end_date")
    private LocalDateTime projectEndDate;
    @OneToMany(mappedBy = "projects")
    @JsonManagedReference
    private List<Tasks> tasks;
    // @ManyToMany
    // @JoinTable(name = "project_tasks", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "tasks_id"))
    // @JsonManagedReference
    // private Set<Tasks> tasks;
    @ManyToOne
    @JoinColumn(name = "project_status_id")
    private Status status;
    @ManyToOne
    @JoinColumn(name = "project_user_id")
    private User users;
    @Column(name = "project_active")
    private boolean projectActive = true;

    @PrePersist
    protected void onCreate() {
        projectCreateDate = LocalDateTime.now();
    }
}
