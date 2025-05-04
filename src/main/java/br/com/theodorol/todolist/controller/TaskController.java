package br.com.theodorol.todolist.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.theodorol.todolist.model.TaskModel;
import br.com.theodorol.todolist.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/task")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class TaskController {
    private final TaskService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TaskModel task, HttpServletRequest request) {

        var currenDate = LocalDateTime.now();

        if (currenDate.isAfter(task.getStartAt()) || currenDate.isAfter(task.getEndAt())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("A data de inicio tem que ser maior que a data atual");
        }

        if (task.getStartAt().isAfter(task.getEndAt())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("A data de inicio tem que ser menor que data de termino");
        }

        var idUser = request.getAttribute("idUsers");
        task.setIdUsers((UUID) idUser);

        return ResponseEntity.ok(service.create(task));
    }

    @GetMapping
    public ResponseEntity<List<TaskModel>> list(HttpServletRequest request) {
        var idUser = request.getAttribute("idUsers");
        var findAllTask = this.service.findAllTask((UUID) idUser);
        return ResponseEntity.ok(findAllTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody TaskModel task) {
        return ResponseEntity.ok(service.updateTask(id, task));
    }
}
