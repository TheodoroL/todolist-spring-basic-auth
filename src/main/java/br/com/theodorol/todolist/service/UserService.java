package br.com.theodorol.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.theodorol.todolist.model.UserModel;
import br.com.theodorol.todolist.repository.IUserRepository;

@Service

public class UserService {
    @Autowired
    private IUserRepository repository;

    public UserModel create(UserModel users) throws RuntimeException {
        UserModel user = repository.findByUsername(users.getUsername());
        if (user != null)
            throw new RuntimeException("Já existe esse cliente");

        var passwordHash = BCrypt.withDefaults().hashToString(12, users.getPassword().toCharArray());
        users.setPassword(passwordHash);
        return repository.save(users);

    }

}
