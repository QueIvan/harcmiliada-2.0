package queivan.harcmiliada.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GroupActions {
    ADD("add"),
    DELETE("del"),
    ADMIN("setAdmin");
    private String option;
}
