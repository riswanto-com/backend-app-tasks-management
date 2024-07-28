package com.tasks.management.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tasks.management.dto.request.StatusData;
import com.tasks.management.dto.response.ResponseData;
import com.tasks.management.models.Status;
import com.tasks.management.services.StatusServices;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/status")
public class StatusController {
    @Autowired
    private StatusServices statusServices;
    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<Page<Status>> getList(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String type,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Status> status = statusServices.getList(name,type, page, size);
        return ResponseEntity.ok(status);
    }

    @GetMapping("/{id}")
    public Status findOne(@PathVariable("id") Integer id) {
        return statusServices.findOne(id);
    }

    @DeleteMapping("/{id}")
    public Status deleteData(@PathVariable("id") Integer id) {
        return statusServices.updateActive(id);
    }

    @PostMapping
    public ResponseEntity<ResponseData<Status>> create(@Valid @RequestBody StatusData statusData,
            Errors errors) {
        ResponseData<Status> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Status status = mapper.map(statusData, Status.class);
        responseData.setStatus(true);
        responseData.setData(statusServices.save(status));

        return ResponseEntity.ok(responseData);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Status>> update(@Valid @RequestBody StatusData statusData,
            Errors errors) {
        ResponseData<Status> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Status status = mapper.map(statusData, Status.class);
        Status data = statusServices.updatestatus(status);
        responseData.setStatus(true);
        responseData.setData(data);
        return ResponseEntity.ok(responseData);
    }

}
