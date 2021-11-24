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
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Question> questions;
    @NotNull
    private String ownerId;
    private LocalDateTime createdAt;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Question currentQuestion;

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void setCurrentQuestion(Question question) {
        this.currentQuestion = question;
    }
}
