package queivan.harcmiliada.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity(name = "_questions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Question {
    @Id
    @NotNull
    @GeneratedValue
    private UUID id;
    @NotNull
    private String content;
    @OneToMany(mappedBy = "question", cascade = javax.persistence.CascadeType.ALL)
    private List<Answer> answers;
    @ManyToMany(mappedBy = "questions", cascade = javax.persistence.CascadeType.ALL)
    private List<Game> games;
    @NotNull
    private String creatorId;
    @NotNull
    @Builder.Default
    private boolean inPublicLib = false;
    private LocalDateTime createdAt;
    @OneToMany(mappedBy = "currentQuestion", cascade = javax.persistence.CascadeType.ALL)
    private List<Game> currentInGame;

    public void setPublic() {
        this.inPublicLib = !this.inPublicLib;
    }

}
