package in.hp.java.springdataredisstarter.controller;

import in.hp.java.springdataredisstarter.domain.Todo;
import in.hp.java.springdataredisstarter.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping
    public List<Todo> getTodos() {
        return StreamSupport
                .stream(todoRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
