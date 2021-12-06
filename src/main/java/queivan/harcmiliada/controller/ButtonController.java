package queivan.harcmiliada.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import queivan.harcmiliada.domain.ButtonDto;
import queivan.harcmiliada.facade.ButtonFacade;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/button")
@RequiredArgsConstructor
public class ButtonController {
    private final ButtonFacade facade;

    @GetMapping("/{buttonCode}/{userId}")
    public UUID getButtonsGameId(@PathVariable("buttonCode") String buttonCode, @PathVariable("userId") String userId) {
        return facade.getButtonsGameId(buttonCode, userId);
    }

    @GetMapping(value = "/{userId}", produces = APPLICATION_JSON_VALUE)
    public List<ButtonDto> getButtons(@PathVariable("userId") String userId) {
        return facade.getButtons(userId);
    }

    @PostMapping(value = "/{userId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ButtonDto createButton(@RequestBody ButtonDto buttonDto, @PathVariable("userId") String userId) {
        return facade.createButton(buttonDto, userId);
    }

    @PutMapping(value = "/{buttonId}/current/{gameId}/{userId}", produces = APPLICATION_JSON_VALUE)
    public ButtonDto setButtonsGame(@PathVariable("buttonId") UUID buttonId, @PathVariable("gameId") UUID gameId, @PathVariable("userId") String userId){
        return facade.setButtonsGame(buttonId, gameId, userId);
    }

    @DeleteMapping(value = "/{buttonId}/{userId}")
    public void deleteButton(@PathVariable("buttonId") UUID buttonId, @PathVariable("userId") String userId){
        facade.deleteButton(buttonId, userId);
    }

}
