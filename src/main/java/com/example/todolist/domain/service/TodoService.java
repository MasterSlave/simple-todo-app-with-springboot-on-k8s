package com.example.todolist.domain.service;

import com.example.todolist.domain.entity.ToDoItem;
import com.example.todolist.domain.repository.ToDoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private static final Logger logger = LoggerFactory.getLogger(TodoService.class);

    private final ToDoRepository toDoRepository;

    public TodoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    public List<ToDoItem> retrieveTodos() {
        return toDoRepository.findByCompleted(false);
    }

    public List<ToDoItem> retrieveCompletedTodos() {
        return toDoRepository.findByCompleted(true);
    }

    public void addTodo(String toDoItemValue) {
        if(findByValue(toDoItemValue).isPresent()){
            throw new RuntimeException("Already Exists");
        }
        logger.info("new item added: {}", toDoItemValue);
        toDoRepository.save(new ToDoItem(toDoItemValue));
    }

    public void updateTodo(String toDoItemValue) {
        Optional<ToDoItem> toDoItem = findByValue(toDoItemValue);
        logger.info("item to be updated: {}", toDoItemValue);
        toDoItem.ifPresent(toDoItem1 -> {
            toDoItem1.setCompleted(true);
            toDoRepository.save(toDoItem1);
            logger.info("item is updated: {}", toDoItem1.getValue());
        });
    }

    public void deleteTodo(String toDoItemValue) {
        Optional<ToDoItem> toDoItem = findByValue(toDoItemValue);
        logger.info("item to be deleted: {}", toDoItemValue);
        toDoItem.ifPresent(toDoRepository::delete);
    }

    private Optional<ToDoItem> findByValue(String toDoItemValue) {
        return toDoRepository.findByValue(toDoItemValue);
    }
}
