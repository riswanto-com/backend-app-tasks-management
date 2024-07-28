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

import com.tasks.management.dto.notes.NotesData;
import com.tasks.management.dto.response.ResponseData;
import com.tasks.management.dto.response.ResponseDataService;
import com.tasks.management.models.Note;
import com.tasks.management.services.project.ProjectServices;
import com.tasks.management.services.tasks.TasksServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/notes")
public class NoteController {
    @Autowired
    private ProjectServices projectServices;

    @Autowired
    private TasksServices tasksServices;

    @Autowired
    private ModelMapper mapper;

    // /list?id=1&page=1&size=1
    @GetMapping("/list")
    public ResponseEntity<Page<Note>> getProjects(
            @RequestParam(required = false) Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Note> noteList = projectServices.getListNote(id, page, size);
        return ResponseEntity.ok(noteList);
    }
    @GetMapping("/task")
    public ResponseEntity<Page<Note>> getTaskList(
            @RequestParam(required = false) Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Note> noteList = tasksServices.getListNote(id, page, size);
        return ResponseEntity.ok(noteList);
        // System.out.println("xxxx");
        // return null;
    }

    @GetMapping("/{id}")
    public Note getId(@PathVariable("id") Long id) {
        return projectServices.findNoteId(id);
    }

    @DeleteMapping("/{id}")
    public ResponseDataService<?> deleteId(@PathVariable("id") Long id) {
        ResponseDataService<?> resData = projectServices.deleteNote(id);
        return resData;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody NotesData notesData,
            Errors errors) {
        ResponseData<?> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Note notes = mapper.map(notesData, Note.class);
        ResponseDataService<?> resData = projectServices.saveNote(notes);

        return ResponseEntity.ok(resData);
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody NotesData notesData,
            Errors errors) {
        ResponseData<?> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Note notes = mapper.map(notesData, Note.class);
        ResponseDataService<?> resData = projectServices.updateNote(notes);

        return ResponseEntity.ok(resData);
    }
}
