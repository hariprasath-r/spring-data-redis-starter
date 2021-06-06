package in.hp.java.springdataredisstarter.config;

import in.hp.java.springdataredisstarter.domain.Todo;
import in.hp.java.springdataredisstarter.repository.TodoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandLineRunner {

    @Bean
    public org.springframework.boot.CommandLineRunner loadData(TodoRepository repository) {
        return args -> {
            repository.deleteAll();
            repository.save(Todo.builder().title("Wake up").build());
            repository.save(Todo.builder().title("Fall out of bed").build());
            repository.save(Todo.builder().title("Drag a comb across your head").build());
        };
    }
}
