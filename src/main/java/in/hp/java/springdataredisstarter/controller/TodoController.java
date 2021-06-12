package in.hp.java.springdataredisstarter.controller;

import in.hp.java.springdataredisstarter.domain.Todo;
import in.hp.java.springdataredisstarter.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * CrossOrigin - helps to configure CORS properties for this controller
 * method           - mentions which HTTP methods are permitted
 * maxAge           - mentions maximum time to cache pre-flight responses. default 1800 - 30min
 * allowedHeaders   - mentions which http headers are allowed
 * origin           - matched by host, uri, port from which the requests are allowed
 */
@CrossOrigin(
        methods = {POST, GET, PUT, DELETE, PATCH, OPTIONS},
        maxAge = 3600,
        allowedHeaders = {"x-requested-with", "origin", "content-type", "accept", "accept-patch"},
        origins = "*"
)
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

    @GetMapping("/{id}")
    public Todo getTodo(@PathVariable("id") Long id) {
        return todoRepository.findById(id).orElse(null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Todo addTodo(@RequestBody Todo todo) {
        var saved = todoRepository.save(todo);
        String href = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                .methodOn(this.getClass())
                .getTodo(saved.getId()))
                .withSelfRel().getHref();
        saved.setUrl(href);
        return saved;
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.GONE)
    public void deleteTodos() {
        todoRepository.deleteAll();
    }
}
