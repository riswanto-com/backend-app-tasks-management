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

import com.tasks.management.dto.response.ResponseData;
import com.tasks.management.dto.tasks.TasksData;
import com.tasks.management.models.Tasks;
import com.tasks.management.services.tasks.TasksServices;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/tasks")
public class TasksController {

    @Autowired
    private TasksServices tasksServices;
    @Autowired
    private ModelMapper mapper;

    @GetMapping("/{id}")
    public Tasks findOne(@PathVariable("id") Long id) {
        return tasksServices.findOne(id);
    }

    @PostMapping
    public ResponseEntity<ResponseData<Tasks>> create(@Valid @RequestBody TasksData tasksData,
            Errors errors) {
        ResponseData<Tasks> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Tasks tasks = mapper.map(tasksData, Tasks.class);
        responseData.setStatus(true);
        responseData.setData(tasksServices.save(tasks));

        return ResponseEntity.ok(responseData);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Tasks>> update(@Valid @RequestBody TasksData tasksData,
            Errors errors) {
        ResponseData<Tasks> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Tasks tasks = mapper.map(tasksData, Tasks.class);
        responseData.setStatus(true);
        responseData.setData(tasksServices.save(tasks));

        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<Tasks>> getTaskss(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Tasks> tasksList = tasksServices.getTasks(name, page, size);
        return ResponseEntity.ok(tasksList);
    }

    @DeleteMapping("/{id}")
    public Tasks deleteData(@PathVariable("id") Long id) {
        return tasksServices.updateActive(id);
    }

}
