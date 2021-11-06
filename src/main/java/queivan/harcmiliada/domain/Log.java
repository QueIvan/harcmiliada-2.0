package queivan.harcmiliada.domain;

import com.sun.istack.NotNull;
import lombok.*;
import queivan.harcmiliada.domain.enums.LogType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "_logs")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Log {
    @Id
    @NotNull
    @GeneratedValue
    private UUID id;
    @NotNull
    private LogType type;
    @NotNull
    private String message;
    @NotNull
    private UUID userId;
    private LocalDateTime createdAt;
}
