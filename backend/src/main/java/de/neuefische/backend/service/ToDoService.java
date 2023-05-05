package de.neuefische.backend.service;

import de.neuefische.backend.exceptions.ToDoNotFoundException;
import de.neuefische.backend.model.ToDo;
import de.neuefische.backend.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToDoService {

    private final ToDoRepository repository;
    private final GenerateUUID generateUUID;
    public List<ToDo> getAllToDos() {
        return repository.getAllToDos();
    }

    public void addToDo(ToDo todo) {
        todo.setId(generateUUID.generateUUID());
        repository.addToDo(todo);
    }

    public ToDo getToDoById(String id) {
        return repository.getToDoById(id);
    }

    public void deleteProductById(String id) {
        if (!repository.toDoExists(id)) {
            throw new ToDoNotFoundException(id);
        } else {
            repository.deleteProductById(id);
        }
    }

    public ToDo updateToDo(String id, ToDo todo) {
        if (!repository.toDoExists(id)) {
            throw new ToDoNotFoundException(id);
        } else {
            return repository.updateToDo(id, todo);
        }
    }
}
