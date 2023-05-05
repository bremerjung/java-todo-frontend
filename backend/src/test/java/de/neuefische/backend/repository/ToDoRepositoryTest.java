package de.neuefische.backend.repository;

import de.neuefische.backend.model.ToDo;
import de.neuefische.backend.model.ToDoStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ToDoRepositoryTest {

    ToDoRepository repo = new ToDoRepository();

    @Test
    void getAllToDos() {
        // given
        List<ToDo> expected = new ArrayList<>();
        expected.add(new ToDo("1", "Rasen mähen", ToDoStatus.OPEN));
        expected.add(new ToDo("2", "Wäsche waschen", ToDoStatus.OPEN));
        expected.add(new ToDo("3", "Kuchen essen", ToDoStatus.DONE));
        expected.add(new ToDo("4", "Freitagsaufgabe", ToDoStatus.IN_PROGRESS));

        // when
        List<ToDo> actual = repo.getAllToDos();

        // then
        assertEquals(expected, actual);
        assertThat(actual).isEqualTo(expected); // using assertJ
    }

    @Test
    void addToDo() {
        // given
        List<ToDo> expected = new ArrayList<>();
        expected.add(new ToDo("1", "Rasen mähen", ToDoStatus.OPEN));
        expected.add(new ToDo("2", "Wäsche waschen", ToDoStatus.OPEN));
        expected.add(new ToDo("3", "Kuchen essen", ToDoStatus.DONE));
        expected.add(new ToDo("4", "Freitagsaufgabe", ToDoStatus.IN_PROGRESS));
        ToDo todoToAdd = new ToDo("4711", "Fußbal gucken", ToDoStatus.OPEN);
        expected.add(todoToAdd);

        // when
        List<ToDo> actual = repo.addToDo(todoToAdd);

        // then
        assertEquals(expected, actual);
    }

    @Test
    void getToDoById() {
        // given
        String id = "3";
        ToDo expected = new ToDo("3", "Kuchen essen", ToDoStatus.DONE);

        // when
        ToDo actual = repo.getToDoById(id);

        // then
        assertEquals(expected, actual);
    }

    @Test
    void deleteProductById() {
        // given
        String id = "3";
        List<ToDo> expected = new ArrayList<>();
        expected.add(new ToDo("1", "Rasen mähen", ToDoStatus.OPEN));
        expected.add(new ToDo("2", "Wäsche waschen", ToDoStatus.OPEN));
        expected.add(new ToDo("4", "Freitagsaufgabe", ToDoStatus.IN_PROGRESS));

        // when
        List<ToDo> actual = repo.deleteProductById(id);

        // then
        assertEquals(expected, actual);
    }

    @Test
    void toDoExists_postiveCase() {
        // given
        String id = "1";

        // when
        boolean actual = repo.toDoExists(id);

        // then
        assertTrue(actual);
    }

    @Test
    void toDoExists_negativeCase() {
        // given
        String id = "4711";

        // when
        boolean actual = repo.toDoExists(id);

        // then
        assertFalse(actual);
    }

    @Test
    void updateToDo() {
        // given
        String id = "3";
        ToDo expected = new ToDo("3", "Fußbal gucken", ToDoStatus.OPEN);

        // when
        ToDo actual = repo.updateToDo(id, expected);

        // then
        assertEquals(expected, actual);
    }
}