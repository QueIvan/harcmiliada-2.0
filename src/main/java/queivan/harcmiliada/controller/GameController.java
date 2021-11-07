package queivan.harcmiliada.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import queivan.harcmiliada.domain.GameDto;
import queivan.harcmiliada.domain.GameQuestionDto;
import queivan.harcmiliada.facade.GameFacade;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
public class GameController {
    private final GameFacade facade;

    @GetMapping(value = "/{gameId}/current/{userId}", produces = APPLICATION_JSON_VALUE)
    public GameQuestionDto getGamesCurrentQuestion(@PathVariable("gameId") UUID gameId, @PathVariable("userId") String userId) {
        return facade.getGamesCurrentQuestion(gameId, userId);
    }

    @GetMapping(value = "/owner/{ownerId}/{userId}", produces = APPLICATION_JSON_VALUE)
    public List<GameDto> getAllGamesByOwnerId(@PathVariable("ownerId") UUID ownerId, @PathVariable("userId") String userId) {
        return facade.getAllGamesByOwnerId(ownerId, userId);
    }

    @GetMapping(value="/{gameId}/{userId}", produces = APPLICATION_JSON_VALUE)
    public GameDto getGameById(@PathVariable("gameId") UUID gameId, @PathVariable("userId") String userId) {
        return facade.getGameById(gameId, userId);
    }

    @GetMapping(value = "/{userId}", produces = APPLICATION_JSON_VALUE)
    public List<GameDto> getAllGames(@PathVariable("userId") String userId) {
        return facade.getAllGames(userId);
    }

    @PostMapping(value="/{userId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public GameDto createGame(@RequestBody GameDto game, @PathVariable("userId") String userId) {
        return facade.createGame(game, userId);
    }

    @PutMapping(value="/{userId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public GameDto updateGame(@RequestBody GameDto game, @PathVariable("userId") String userId) {
        return facade.updateGame(game, userId);
    }

    @DeleteMapping(value = "/{gameId}/{userId}")
    public void deleteGame(@PathVariable("gameId") UUID gameId, @PathVariable("userId") String userId) {
        facade.deleteGame(gameId, userId);
    }

}
