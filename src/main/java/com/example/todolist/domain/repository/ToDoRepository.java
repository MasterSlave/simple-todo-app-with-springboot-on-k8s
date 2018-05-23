package com.example.todolist.domain.repository;

import com.example.todolist.domain.entity.ToDoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ToDoRepository extends JpaRepository<ToDoItem, Long> {

    Optional<ToDoItem> findByValue(String toDoItemValue);

    List<ToDoItem> findByCompleted(boolean completed);

}
