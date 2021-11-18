package queivan.harcmiliada.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionDto {
    //Hello
    private UUID id;
    private String content;
    private List<QuestionAnswerDto> answers;
    private List<QuestionGameDto> games;
    private String creatorId;
    private boolean isPublic;
    private LocalDateTime createdAt;
    private List<QuestionGameDto> currentInGame;
}
