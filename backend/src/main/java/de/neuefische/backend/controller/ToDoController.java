package de.neuefische.backend.controller;

import de.neuefische.backend.model.ToDo;
import de.neuefische.backend.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ToDoController {

    private final ToDoService service;

    @GetMapping("todo/{id}")
    public ToDo getToDoById(@PathVariable String id) {
        return service.getToDoById(id);
    }

    @GetMapping("todo")
    public List<ToDo> getAllToDos() {
        return service.getAllToDos();
    }

    @PostMapping("todo")
    public void addTodo(@RequestBody ToDo todo) {
        service.addToDo(todo);
    }

    @PutMapping("todo/{id}")
    public ToDo updateTodo(@PathVariable String id, @RequestBody ToDo todo) {
        return service.updateToDo(id, todo);
    }

    @DeleteMapping("todo/{id}")
    public void deleteToDo(@PathVariable String id) {
        service.deleteProductById(id);
    }



}
