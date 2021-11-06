package queivan.harcmiliada.domain;

import lombok.*;
import queivan.harcmiliada.domain.enums.LogType;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogDto {
    private UUID id;
    private LogType type;
    private String message;
    private UUID userId;
    private LocalDateTime createdAt;
}
