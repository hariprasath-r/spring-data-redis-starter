package in.hp.java.springdataredisstarter.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import static java.util.Objects.nonNull;

/**
 * RedisHash - annotation is used to treat this entity as a persistence unit into redis, instead of caching
 * Can be considered as a replacement for @Entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
@RedisHash
public class Todo {
    @Id
    private Long id;

    @Setter
    private String title;

    @Setter
    @Builder.Default
    private Boolean completed = false;

    @Setter
    private Long order;

    @Setter
    private String url;

    public void partialUpdate(Todo todo) {
        if (nonNull(todo.getTitle())) {
            this.setTitle(todo.getTitle());
        }
        if (nonNull(todo.getCompleted())) {
            this.setCompleted(todo.getCompleted());
        }
        if (nonNull(todo.getOrder())) {
            this.setOrder(todo.getOrder());
        }
    }
}
