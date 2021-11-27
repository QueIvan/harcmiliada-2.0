package queivan.harcmiliada.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity(name = "_groups")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Group {
    @Id
    @NotNull
    @GeneratedValue
    private UUID id;
    @NotNull
    private String name;
    @NotNull
    private String creator;
    @NotNull
    @ElementCollection
    private List<String> users;

    public void setUsers(List<String> users) {
        this.users = users;
    }

}
