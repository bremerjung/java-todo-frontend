package de.neuefische.backend.exceptions;

public class ToDoNotFoundException extends RuntimeException {
    public ToDoNotFoundException(String id) {
        super("ToDo with id " + id + " does not exist");
    }
}
