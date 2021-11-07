package queivan.harcmiliada.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity(name = "_games")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Game {
    @Id
    @NotNull
    @GeneratedValue
    private UUID id;
    @NotNull
    private String name;
    @ManyToMany(cascade = {javax.persistence.CascadeType.PERSIST, javax.persistence.CascadeType.MERGE})
    private List<Question> questions;
    @NotNull
    private String ownerId;
    private LocalDateTime createdAt;
    @ManyToOne(cascade = {javax.persistence.CascadeType.PERSIST, javax.persistence.CascadeType.MERGE})
    private Question currentQuestion;
}
