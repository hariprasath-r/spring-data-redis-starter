package in.hp.java.springdataredisstarter.repository;

import in.hp.java.springdataredisstarter.domain.Todo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo, Long> {
}
