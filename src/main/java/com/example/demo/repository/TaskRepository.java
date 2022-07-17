package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Task;


public interface TaskRepository extends JpaRepository<Task,Long> {

    Optional<Task> findByName(String name);

    Optional<Task> deleteTaskById(long id);
}



