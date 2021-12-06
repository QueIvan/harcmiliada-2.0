package queivan.harcmiliada.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity(name = "_buttons")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Button {
    @Id
    @NotNull
    @GeneratedValue
    private UUID id;
    @ManyToOne
    private Game current;
    @NotNull
    private String qrCode;
    @NotNull
    private String ownerId;

    public void setCurrent(Game current) {
        this.current = current;
    }

}
