package de.neuefische.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.neuefische.backend.exceptions.ToDoNotFoundException;
import de.neuefische.backend.model.ToDo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class IntegrationTestsForToDoController {

    @Autowired
    MockMvc mockMvc;

    @Test
    void whenGetToDo_ThenReturnToDo_AndStatusCode200() throws Exception {
        // given
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/api/todo/1");

        // when & then
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "id": "1",
                            "description": "Rasen mähen",
                            "status": "OPEN"
                        }
                        """));
    }

    @Test
    void whenGetToDoIsUsedWithIllegalArgument_ThenExceptionIsThrown() {
        // given
        String id = "bla";
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/api/todo/bla");

        // when & then
        try {
            mockMvc.perform(request).toString();
        } catch (Exception e) {
            //assertThat(e).isInstanceOf(NoSuchElementException.class);
            assertThat(e.getMessage()).isEqualTo("Request processing failed: java.util.NoSuchElementException: No todo with id " + id + " found in this todo repo.");
        }
    }

    @Test
    void whenGetAllToDos_ThenReturnAllToDos_AndStatusCode200() throws Exception {
        // given
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/api/todo");

        // when & then
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [
                             {
                                 "id": "1",
                                 "description": "Rasen mähen",
                                 "status": "OPEN"
                             },
                             {
                                 "id": "2",
                                 "description": "Wäsche waschen",
                                 "status": "OPEN"
                             },
                             {
                                 "id": "3",
                                 "description": "Kuchen essen",
                                 "status": "DONE"
                             },
                             {
                                 "id": "4",
                                 "description": "Freitagsaufgabe",
                                 "status": "IN_PROGRESS"
                             }
                         ]
                        """));
    }

    @Test
    @DirtiesContext
    void whenAddToDo_ThenToDoIsAdded_AndReturnStatusCode200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/todo")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "id": "5",
                            "description": "Sonne genießen",
                            "status": "OPEN"
                        }
                        """)).andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/todo"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [
                             {
                                 "id": "1",
                                 "description": "Rasen mähen",
                                 "status": "OPEN"
                             },
                             {
                                 "id": "2",
                                 "description": "Wäsche waschen",
                                 "status": "OPEN"
                             },
                             {
                                 "id": "3",
                                 "description": "Kuchen essen",
                                 "status": "DONE"
                             },
                             {
                                 "id": "4",
                                 "description": "Freitagsaufgabe",
                                 "status": "IN_PROGRESS"
                             },
                             {
                                 "description": "Sonne genießen",
                                 "status": "OPEN"
                        }
                         ]
                        """));
    }

    @Test
    @DirtiesContext
    void whenUpdateToDo_ThenReturnUpdatedToDoItem_AndStatusCode200() throws Exception {
        String id = "3";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/api/todo/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                            {
                                "description": "Buch lesen",
                                "status": "OPEN"
                            }
                        """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
{
                                "description": "Buch lesen",
                                "status": "OPEN"
                            }
"""))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        ToDo todo = objectMapper.readValue(content, ToDo.class);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/todo/" + todo.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "description": "Buch lesen",
                            "status": "OPEN"
                        }
                        """));
    }

    @Test
    @DirtiesContext
    void whenDeleteToDo_ThenReturnStatusCode200() {

    }

}