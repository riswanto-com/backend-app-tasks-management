package com.tasks.management.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tasks.management.dto.project.ProjectData;
import com.tasks.management.dto.response.ResponseData;
import com.tasks.management.models.Project;
import com.tasks.management.services.project.ProjectServices;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectServices projectServices;
    @Autowired
    private ModelMapper mapper;

    @GetMapping("/{id}")
    public Project findOne(@PathVariable("id") Long id) {
        return projectServices.findOne(id);
    }

    @PostMapping
    public ResponseEntity<ResponseData<Project>> create(@Valid @RequestBody ProjectData projectData,
            Errors errors) {
        ResponseData<Project> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Project project = mapper.map(projectData, Project.class);
        responseData.setStatus(true);
        responseData.setData(projectServices.save(project));

        return ResponseEntity.ok(responseData);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Project>> update(@Valid @RequestBody ProjectData projectData,
            Errors errors) {
        ResponseData<Project> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Project project = mapper.map(projectData, Project.class);
        responseData.setStatus(true);
        responseData.setData(projectServices.updateData(project));
        // responseData.setData(project);

        return ResponseEntity.ok(responseData);
    }
    @GetMapping("/list")
    public ResponseEntity<Page<Project>> getProjects(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Project> projects = projectServices.getProjects(name, page, size);
        return ResponseEntity.ok(projects);
    }
    @DeleteMapping("/{id}")
    public Project deleteData(@PathVariable("id") Long id) {
        return projectServices.updateActive(id);
    }

}
