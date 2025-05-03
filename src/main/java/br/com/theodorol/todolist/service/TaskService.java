package br.com.theodorol.todolist.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.theodorol.todolist.model.TaskModel;
import br.com.theodorol.todolist.repository.ITaskRepository;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class TaskService {
    @Autowired
    ITaskRepository repository;

    public TaskModel create(TaskModel task) {
        return repository.save(task);
    }

    public List<TaskModel> findAllTask(UUID useriD) {
        var tasks = this.repository.findByIdUsers(useriD);
        return tasks;
    }

    public TaskModel updateTask(UUID id, TaskModel task, HttpServletRequest request) {
        var userId = request.getAttribute("idUsers");
        task.setId(id);
        task.setIdUsers((UUID) userId);
        task.setCreatedAt(LocalDateTime.now());
        return this.repository.save(task);
    }
}
