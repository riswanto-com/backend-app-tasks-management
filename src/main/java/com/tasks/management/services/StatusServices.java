package com.tasks.management.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tasks.management.models.Status;
import com.tasks.management.repositories.StatusRepos;
import java.util.*;

@Service
public class StatusServices {
    @Autowired
    private StatusRepos statusRepos;

    public Status save(Status status) {
        return statusRepos.save(status);
    }

    public Status updatestatus(Status status) {
        Status existingStatus = statusRepos.findById(status.getId()).orElseThrow(() -> new RuntimeException("Not Found"));
        existingStatus.setStatusName(status.getStatusName());
        existingStatus.setStatusType(status.getStatusType());
        statusRepos.save(existingStatus);
        return existingStatus;
    }

    public Status findOne(Integer id) {
        Optional<Status> category = statusRepos.findById(id);
        if (!category.isPresent()) {
            return null;
        }
        return category.get();
    }

    public Iterable<Status> findAll() {
        return statusRepos.findAll();
    }


    public Page<Status> getList(String name,String type, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return statusRepos.findByDataList(name,type, pageable);
    }

    public Status updateActive(Integer id) {
        Optional<Status> taskOptional = statusRepos.findById(id);
        if (taskOptional.isPresent()) {
            Status statusData = taskOptional.get();
            statusData.setStatusAvailable(false);
            return statusRepos.save(statusData);
        } else {
            throw new RuntimeException("Task not found");
        }
    }
}
