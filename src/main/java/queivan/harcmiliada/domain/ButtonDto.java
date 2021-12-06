package queivan.harcmiliada.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ButtonDto {
    private UUID id;
    private GameDto current;
    private String qrCode;
    private String ownerId;
}
