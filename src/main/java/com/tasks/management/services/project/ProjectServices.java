package com.tasks.management.services.project;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tasks.management.dto.log.LogData;
import com.tasks.management.dto.response.ResponseDataService;
import com.tasks.management.models.HistoryLog;
import com.tasks.management.models.Note;
import com.tasks.management.models.Project;
import com.tasks.management.models.Status;
import com.tasks.management.models.User;
import com.tasks.management.repositories.HistoriesLogRepos;
import com.tasks.management.repositories.NotesRepos;
import com.tasks.management.repositories.ProjectRepos;
import com.tasks.management.repositories.StatusRepos;
import com.tasks.management.repositories.UserRepos;
import com.tasks.management.services.AuthService;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
public class ProjectServices {
    @Autowired
    private ProjectRepos projectRepos;
    @Autowired
    private UserRepos userRepos;
    @Autowired
    private StatusRepos statusRepos;
    @Autowired
    private AuthService authService;
    @Autowired
    private HistoriesLogRepos historiesLogRepos;
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private NotesRepos notesRepos;

    public Project save(Project projectCreate) {
        LogData log = new LogData();
        String userId = authService.getAuthenticatedUserId();
        Optional<User> userData = userRepos.findByUsername(userId);
        if (userData.isPresent()) {
            projectCreate.setProjectCreateId(userData.get().getId());
            projectCreate.setUsers(userData.get());
            log.setUsers(userData.get());
        }
        Optional<Status> statusData = statusRepos.findByTypeStatus("ToDo", "PROJECT");
        if (statusData.isPresent()) {
            projectCreate.setStatus(statusData.get());

        }
        projectCreate.setProjectCreateDate(LocalDateTime.now());

        Project res = projectRepos.save(projectCreate);

        log.setLogAction("Add project");
        log.setProjects(res);
        log.setLogCreateDate(LocalDateTime.now());
        log.setLogDescription("Membuat project dengan nama" + projectCreate.getProjectName());
        saveLog(log).getLogDescription();
        return res;
        // return projectCreate;
    }

    public Project updateData(Project project) {
        LogData log = new LogData();
        Project existingProject = projectRepos.findById(project.getId())
                .orElseThrow(() -> new RuntimeException("Not Found"));
        if (project.getUsers() != null) {
            existingProject.setUsers(project.getUsers());
            log.setUsers(project.getUsers());
        }
        if (project.getStatus() != null) {
            existingProject.setStatus(project.getStatus());
            String nameStatus = project.getStatus().getStatusName();
            if ("Done".equals(nameStatus)) {
                existingProject.setProjectEndDate(LocalDateTime.now());
            }
        }
        if (project.getProjectName() != null) {

            existingProject.setProjectName(project.getProjectName());
        }
        if (project.getProjectDescription() != null) {

            existingProject.setProjectDescription(project.getProjectDescription());
        }

        return projectRepos.save(existingProject);
    }

    public Project updateActive(Long project) {

        Project existingProject = projectRepos.findById(project).orElseThrow(() -> new RuntimeException("Not Found"));

        existingProject.setProjectActive(false);
        return projectRepos.save(existingProject);
    }

    public Project findOne(Long id) {
        Optional<Project> project = projectRepos.findById(id);
        if (!project.isPresent()) {
            return null;
        }
        return project.get();
    }

    public Page<Project> getProjects(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        String userId = authService.getAuthenticatedUserId();
        Optional<User> userData = userRepos.findByUsername(userId);
        if (!userData.isPresent()) {
            return null;
        }
        return projectRepos.findByNameAndStatus(name, userData.get().getId(), pageable);
    }

    public HistoryLog saveLog(LogData historyLogs) {
        HistoryLog createLog = mapper.map(historyLogs, HistoryLog.class);
        HistoryLog saveData = historiesLogRepos.save(createLog);
        return saveData;
    }

    public ResponseDataService<?> saveNote(Note note) {
        LogData log = new LogData();
        ResponseDataService<?> responseData = new ResponseDataService<>();
        String userId = authService.getAuthenticatedUserId();
        Optional<User> userData = userRepos.findByUsername(userId);
        if (userData.isPresent()) {
            note.setUsers(userData.get());
            log.setUsers(userData.get());
        }
        note.setNoteType(true);
        note.setNoteCreateDate(LocalDateTime.now());
        notesRepos.save(note);
        responseData.setMessages("success create note");
        responseData.setStatus(true);
        return responseData;
    }

    public ResponseDataService<?> updateNote(Note note) {
        LogData log = new LogData();
        ResponseDataService<?> responseData = new ResponseDataService<>();
        Note existingNotes = notesRepos.findById(note.getId()).orElseThrow(() -> new RuntimeException("Not Found"));
        existingNotes.setNoteDescription(note.getNoteDescription());
        log.setUsers(existingNotes.getUsers());
        notesRepos.save(note);
        responseData.setMessages("success update note");
        responseData.setStatus(true);
        return responseData;
    }

    public Note findNoteId(Long id) {
        Optional<Note> notes = notesRepos.findById(id);
        if (!notes.isPresent()) {
            return null;
        }
        return notes.get();
    }

    public Page<Note> getListNote(Long id, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return notesRepos.findByListNotes(id, pageable);
    }

    public ResponseDataService<?> deleteNote(Long id) {
        ResponseDataService<?> responseData = new ResponseDataService<>();
        Note existingNotes = notesRepos.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
        existingNotes.setNoteActive(false);
        notesRepos.save(existingNotes);
        responseData.setMessages("success update note");
        responseData.setStatus(true);
        return responseData;
    }
}
