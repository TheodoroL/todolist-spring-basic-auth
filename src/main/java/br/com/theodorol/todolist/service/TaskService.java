package br.com.theodorol.todolist.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.theodorol.todolist.model.TaskModel;
import br.com.theodorol.todolist.repository.ITaskRepository;
import br.com.theodorol.todolist.utils.Utils;
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

    public TaskModel updateTask(UUID id, TaskModel taskUpdate, HttpServletRequest request) throws RuntimeException {
        TaskModel task = this.repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Essa tarefa não existe"));

        if (!task.getIdUsers().equals((UUID) request.getAttribute("idUsers"))) {
            throw new RuntimeException("O usuário não tem permissão para alterar essa tarefa!");
        }
        Utils.getNonNullProperties(taskUpdate, task);
        return this.repository.save(task);
    }
}
