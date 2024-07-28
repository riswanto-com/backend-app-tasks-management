package com.tasks.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tasks.management.models.HistoryLog;

public interface HistoriesLogRepos extends JpaRepository<HistoryLog,Long> {
    
}
