package queivan.harcmiliada.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import queivan.harcmiliada.domain.GameDto;
import queivan.harcmiliada.domain.GameQuestionDto;
import queivan.harcmiliada.service.GameService;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GameFacade {
    private final GameService gameService;

    public GameQuestionDto getGamesCurrentQuestion(UUID gameId, String userId) {
        return gameService.getGamesCurrentQuestion(gameId, userId);
    }

    public List<GameDto> getAllGamesByOwnerId(UUID id, String userId) {
        return gameService.getAllGamesByOwnerId(id, userId);
    }

    public GameDto getGameById(UUID id, String userId) {
        return gameService.getGameById(id, userId);
    }

    public GameDto createGame(GameDto gameDto, String userId) {
        return gameService.createGame(gameDto, userId);
    }

    public GameDto updateGame(GameDto gameDto, String userId) {
        return gameService.updateGame(gameDto, userId);
    }

    public void deleteGame(UUID id, String userId) {
        gameService.deleteGame(id, userId);
    }

    public List<GameDto> getAllGames(String userId) {
        return gameService.getAllGames(userId);
    }
}
