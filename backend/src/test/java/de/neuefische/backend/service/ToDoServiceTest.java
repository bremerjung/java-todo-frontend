package de.neuefische.backend.service;

import de.neuefische.backend.exceptions.ToDoNotFoundException;
import de.neuefische.backend.model.ToDo;
import de.neuefische.backend.model.ToDoStatus;
import de.neuefische.backend.repository.ToDoRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ToDoServiceTest {

    ToDoRepository repo = mock(ToDoRepository.class);

    GenerateUUID generateUUID = mock(GenerateUUID.class);

    ToDoService service = new ToDoService(repo, generateUUID);

    @Test
    void getAllToDos() {
        // given
        List<ToDo> expected = new ArrayList<>();
        when(repo.getAllToDos()).thenReturn(expected);

        // when
        List<ToDo> actual = service.getAllToDos();

        // then
        assertEquals(expected, actual);
        verify(repo).getAllToDos();
    }

    @Test
    void addToDo() {
        // given
        ToDo todoToAdd = new ToDo("4711", "Fußbal gucken", ToDoStatus.OPEN);

        // when
        service.addToDo(todoToAdd);

        // then
        verify(generateUUID).generateUUID();
        verify(repo).addToDo(todoToAdd);
    }

    @Test
    void getToDoById() {
        // given
        String id = "3";
        ToDo expected = new ToDo("3", "Fußbal gucken", ToDoStatus.OPEN);
        when(repo.getToDoById(id)).thenReturn(expected);

        // when
        ToDo actual = service.getToDoById(id);

        // then
        assertEquals(expected, actual);
        verify(repo).getToDoById(id);
    }

    @Test
    void deleteProductById_case_toDo_does_not_exist() {
        // given
        String id = "3";
        when(repo.toDoExists(id)).thenReturn(false);

        // when & then
        assertThrows(ToDoNotFoundException.class, () -> service.deleteProductById(id));
        verify(repo).toDoExists(id);
    }

    @Test
    void deleteProductById_case_toDo_exists() {
        // given
        String id = "3";
        when(repo.toDoExists(id)).thenReturn(true);

        // when
        service.deleteProductById(id);

        // then
        verify(repo).toDoExists(id);
        verify(repo).deleteProductById(id);
    }

    @Test
    void updateToDo_case_toDo_does_not_exist() {
        // given
        String id = "3";
        ToDo updateTodo = new ToDo("4711", "Fußbal gucken", ToDoStatus.OPEN);
        when(repo.toDoExists(id)).thenReturn(false);

        // when & then
        assertThrows(ToDoNotFoundException.class, () -> service.updateToDo(id, updateTodo));
        verify(repo).toDoExists(id);
    }

    @Test
    void updateToDo_case_toDo_exists() {
        // given
        String id = "3";
        ToDo updateTodo = new ToDo("4711", "Fußbal gucken", ToDoStatus.OPEN);
        when(repo.toDoExists(id)).thenReturn(true);

        // when
        ToDo actual = service.updateToDo(id, updateTodo);

        // then
        verify(repo).toDoExists(id);
        verify(repo).updateToDo(id, updateTodo);
    }
}