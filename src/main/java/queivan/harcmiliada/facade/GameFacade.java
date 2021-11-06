package queivan.harcmiliada.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import queivan.harcmiliada.domain.GameDto;
import queivan.harcmiliada.service.GameService;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GameFacade {
    private final GameService gameService;

    public List<GameDto> getAllGamesByOwnerId(UUID id, UUID userId) {
        return gameService.getAllGamesByOwnerId(id, userId);
    }

    public GameDto getGameById(UUID id, UUID userId) {
        return gameService.getGameById(id, userId);
    }

    public GameDto createGame(GameDto gameDto, UUID userId) {
        return gameService.createGame(gameDto, userId);
    }

    public GameDto updateGame(GameDto gameDto, UUID userId) {
        return gameService.updateGame(gameDto, userId);
    }

    public void deleteGame(UUID id, UUID userId) {
        gameService.deleteGame(id, userId);
    }

    public List<GameDto> getAllGames(UUID userId) {
        return gameService.getAllGames(userId);
    }
}
