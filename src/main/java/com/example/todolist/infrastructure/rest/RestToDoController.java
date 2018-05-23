package com.example.todolist.infrastructure.rest;

import com.example.todolist.domain.service.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class RestToDoController {

    private static final Logger logger = LoggerFactory.getLogger(RestToDoController.class);

    private final TodoService todoService;

    public RestToDoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public String index(ModelMap model) {
        model.addAttribute("username", "Burak");
        logger.info("Welcome Burak");
        return "redirect:/todos";
    }

    @GetMapping("/todos")
    public Model retrieveTodos(Model model) {
        model.addAttribute("todosList", todoService.retrieveTodos());
        model.addAttribute("todosDoneList", todoService.retrieveCompletedTodos());
        model.addAttribute("username", "Burak");
        return model;
    }

    @PostMapping("/todos/{toDoItemValue}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ModelAndView addTodo(@PathVariable("toDoItemValue") String toDoItemValue) {
        todoService.addTodo(toDoItemValue);
        return new ModelAndView("redirect:/todos");
    }

    @PutMapping("/todos/{toDoItemValue}")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateTodo(@PathVariable("toDoItemValue") String toDoItemValue) {
        todoService.updateTodo(toDoItemValue);
    }

    @DeleteMapping("/todos/{toDoItemValue}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteTodo(@PathVariable("toDoItemValue") String toDoItemValue) {
        todoService.deleteTodo(toDoItemValue);
    }
}
