package in.hp.java.springdataredisstarter.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

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
}
