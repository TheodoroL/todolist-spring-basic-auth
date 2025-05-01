package br.com.theodorol.todolist.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.theodorol.todolist.model.TaskModel;

public interface ITaskRepository extends JpaRepository<TaskModel, UUID> {

}
