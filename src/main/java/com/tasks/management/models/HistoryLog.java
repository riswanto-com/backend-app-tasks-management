package com.tasks.management.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

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

@Entity
@Table(name = "history_log")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoryLog {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "his_log_id")
    private Long id;
    @Column(name = "his_log_action")
    private String logAction;
    @Column(name = "his_log_description")
    @Lob
    private String logDescription;
    @ManyToOne
    @JoinColumn(name = "his_log_create_id")
    private User users;
    @Column(name = "his_log_create_date")
    private LocalDateTime logCreateDate;
    @ManyToOne
    @JoinColumn(name = "his_log_project_id") 
    @JsonBackReference
    private Project projects;
    @ManyToOne
    @JoinColumn(name = "his_log_tasks_id")
    @JsonBackReference
    private Tasks tasks;
    
}
