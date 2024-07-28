package com.tasks.management.services.tasks;

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
import com.tasks.management.models.Status;
import com.tasks.management.models.Tasks;
import com.tasks.management.models.User;
import com.tasks.management.repositories.HistoriesLogRepos;
import com.tasks.management.repositories.NotesRepos;
import com.tasks.management.repositories.StatusRepos;
import com.tasks.management.repositories.TasksRepos;
import com.tasks.management.repositories.UserRepos;
import com.tasks.management.services.AuthService;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
public class TasksServices {
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
    @Autowired
    private TasksRepos tasksRepos;
    public Tasks save(Tasks tasks) {
        LogData log = new LogData();
        String userId = authService.getAuthenticatedUserId();
        Optional<User> userData = userRepos.findByUsername(userId);
        if (userData.isPresent()) {
            tasks.setTasksCreateId(userData.get().getId());
            tasks.setUsers(userData.get());
            log.setUsers(userData.get());
        }
        Optional<Status> statusData = statusRepos.findByTypeStatus("ToDo", "TASKS");
        if (statusData.isPresent()) {
            tasks.setStatus(statusData.get());
        }
        tasks.setTasksCreateDate(LocalDateTime.now());

        Tasks res = tasksRepos.save(tasks);

        log.setLogAction("Add project");
        log.setTasks(res);
        log.setLogCreateDate(LocalDateTime.now());
        log.setLogDescription("Membuat project dengan nama" + res.getTasksName());
        saveLog(log).getLogDescription();
        return res;
        // return projectCreate;
    }

    public Tasks updateData(Tasks tasks) {
        LogData log = new LogData();
        Tasks existingTasks = tasksRepos.findById(tasks.getId())
                .orElseThrow(() -> new RuntimeException("Not Found"));
        if (tasks.getUsers() != null) {
            existingTasks.setUsers(tasks.getUsers());
            log.setUsers(tasks.getUsers());
        }
        if (tasks.getStatus() != null) {
            existingTasks.setStatus(tasks.getStatus());
            String nameStatus = tasks.getStatus().getStatusName();
            if ("Done".equals(nameStatus)) {
                existingTasks.setTasksEndDate(LocalDateTime.now());
            }
        }
        if (tasks.getTasksName() != null) {

            existingTasks.setTasksName(tasks.getTasksName());
        }
        if (tasks.getTasksDescription() != null) {

            existingTasks.setTasksDescription(tasks.getTasksDescription());
        }

        return tasksRepos.save(existingTasks);
    }

    public Tasks updateActive(Long id) {

        Tasks existingTasks = tasksRepos.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));

        existingTasks.setTasksActive(false);
        return tasksRepos.save(existingTasks);
    }

    public Tasks findOne(Long id) {
        Optional<Tasks> tasksData = tasksRepos.findById(id);
        if (!tasksData.isPresent()) {
            return null;
        }
        return tasksData.get();
    }

    public Page<Tasks> getTasks(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        String userId = authService.getAuthenticatedUserId();
        Optional<User> userData = userRepos.findByUsername(userId);
        if (!userData.isPresent()) {
            return null;
        }
        return tasksRepos.findByListPage(name,userData.get().getId(), pageable);
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
        note.setNoteType(false);
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
        return notesRepos.findByListNotesTasks(id, pageable);
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
