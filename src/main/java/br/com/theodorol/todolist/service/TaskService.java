package br.com.theodorol.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.theodorol.todolist.model.TaskModel;
import br.com.theodorol.todolist.repository.ITaskRepository;

@Service
// c23bebad-e176-41e3-9612-e6134d978856
public class TaskService {
    @Autowired
    ITaskRepository repository;

    public TaskModel create(TaskModel task) {
        return repository.save(task);
    }
}
