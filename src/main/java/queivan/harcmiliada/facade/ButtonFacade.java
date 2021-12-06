package queivan.harcmiliada.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import queivan.harcmiliada.domain.Button;
import queivan.harcmiliada.domain.ButtonDto;
import queivan.harcmiliada.service.ButtonService;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ButtonFacade {
    private final ButtonService service;

    public UUID getButtonsGameId(String buttonName, String userId) {
        return service.getButtonsGameId(buttonName, userId);
    }

    public ButtonDto createButton(ButtonDto dto, String userId) {
        return service.createButton(dto, userId);
    }

    public ButtonDto setButtonsGame(UUID buttonId, UUID gameId, String userId){
        return service.setButtonsGame(buttonId, gameId, userId);
    }

    public void deleteButton(UUID buttonId, String userId){
        service.deleteButton(buttonId, userId);
    }

    public List<ButtonDto> getButtons(String userId) {
        return service.getButtons(userId);
    }
}
