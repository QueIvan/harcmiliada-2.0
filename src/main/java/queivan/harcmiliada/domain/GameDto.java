package queivan.harcmiliada.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GameDto {
    private UUID id;
    private String name;
    private List<GameQuestionDto> questions;
    private String ownerId;
    private LocalDateTime createdAt;
    private GameQuestionDto currentQuestion;
}
