package de.neuefische.backend.repository;

import de.neuefische.backend.model.ToDo;
import de.neuefische.backend.model.ToDoStatus;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ToDoRepository {

    private Map<String, ToDo> todos = new HashMap<>();

    public ToDoRepository() {
        todos.put("1", new ToDo("1", "Rasen mähen", ToDoStatus.OPEN));
        todos.put("2", new ToDo("2", "Wäsche waschen", ToDoStatus.OPEN));
        todos.put("3", new ToDo("3", "Kuchen essen", ToDoStatus.DONE));
        todos.put("4", new ToDo("4", "Freitagsaufgabe", ToDoStatus.IN_PROGRESS));
    }

    public List<ToDo> getAllToDos() {
        return new ArrayList<>(todos.values());
    }

    public List<ToDo> addToDo(ToDo todo) {
        todos.put(todo.getId(), todo);
        return new ArrayList<>(todos.values());
    }

    public ToDo getToDoById(String id) {
        ToDo todo = todos.get(id);
        if (todo == null) {
            throw new NoSuchElementException("No todo with id " + id + " found in this todo repo.");
        }
        return todo;
    }

    public List<ToDo> deleteProductById(String id) {
        todos.remove(id);
        return new ArrayList<>(todos.values());
    }

    public boolean toDoExists(String id) {
        return todos.containsKey(id);
    }

    public ToDo updateToDo(String id, ToDo todo) {
        todo.setId(id);
        todos.replace(id, todo);
        return todo;
    }
}
