package br.com.theodorol.todolist.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.theodorol.todolist.model.TaskModel;
import br.com.theodorol.todolist.service.TaskService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/task")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class TaskController {
    private final TaskService service;

    @PostMapping
    public ResponseEntity<TaskModel> create(@RequestBody TaskModel task) {
        return ResponseEntity.ok(service.create(task));
    }

}
