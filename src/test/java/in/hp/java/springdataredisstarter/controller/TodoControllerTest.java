package in.hp.java.springdataredisstarter.controller;

import in.hp.java.springdataredisstarter.domain.Todo;
import in.hp.java.springdataredisstarter.repository.TodoRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TodoController.class)
class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoRepository todoRepository;

    @Test
    void getTodosReturnsEmptyArrayIfNoTodosPresent() throws Exception {
        mockMvc.perform(get("/todos"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[]"));
    }

    @Test
    void getTodosReturnsAllTodosIfPresent() throws Exception {
        var todos = Collections.singletonList(Todo.builder().title("Learn Redis").build());
        when(todoRepository.findAll()).thenReturn(todos);

        mockMvc.perform(get("/todos"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].title", Matchers.is(todos.get(0).getTitle())));
    }

    @Test
    public void getTodoByIdShouldReturnThatTodo() throws Exception {
        var todo = Todo.builder()
                .id(1212L)
                .title("Todo Returned")
                .build();

        when(todoRepository.findById(any(Long.class))).thenReturn(Optional.of(todo));

        mockMvc.perform(get("/todos/" + todo.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(todo.getTitle())));
    }

    @Test
    void createTodoShouldReturnThatTodo() throws Exception {
        var todo = Todo.builder().title("Learn Redis").build();

        when(todoRepository.save(any(Todo.class))).thenReturn(todo);

        mockMvc.perform(post("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"title\": \"Learn Redis\" }".getBytes()) //
                .characterEncoding("utf-8"))
                .andExpect(status().is(HttpStatus.CREATED.value())) //
                .andExpect(jsonPath("$.title", is(todo.getTitle())));
    }

    @Test
    void deleteAllShouldDeleteAllTodos() throws Exception {
        mockMvc.perform(delete("/todos"))
                .andExpect(status().is(HttpStatus.GONE.value()));

        verify(todoRepository, atLeastOnce()).deleteAll();
    }

}