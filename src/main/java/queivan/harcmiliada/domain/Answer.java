package queivan.harcmiliada.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "_answers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Answer {
    @Id
    @NotNull
    @GeneratedValue
    private UUID id;
    @NotNull
    private String content;
    @NotNull
    private Integer score;
    @ManyToOne
    @NotNull
    private Question question;
    private LocalDateTime createdAt;

    public void setQuestion(Question question){
        this.question = question;
    }

    public void setCreatedAt(LocalDateTime now) {
        this.createdAt = now;
    }
}
