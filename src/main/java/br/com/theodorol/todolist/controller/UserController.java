package br.com.theodorol.todolist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.theodorol.todolist.model.UserModel;
import br.com.theodorol.todolist.service.UserService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")

@AllArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserModel users) {
        try {
            var userCreated = service.create(users);
            return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

}
