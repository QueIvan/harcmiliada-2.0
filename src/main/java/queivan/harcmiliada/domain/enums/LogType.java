package queivan.harcmiliada.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LogType {
    INFO("INFO"),
    ERROR("ERROR"),
    DEBUG("DEBUG");
    private String type;
}
