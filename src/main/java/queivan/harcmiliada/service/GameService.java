package queivan.harcmiliada.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import queivan.harcmiliada.domain.Game;
import queivan.harcmiliada.domain.GameDto;
import queivan.harcmiliada.domain.LogDto;
import queivan.harcmiliada.domain.enums.LogType;
import queivan.harcmiliada.exceptions.GameDoesntExistException;
import queivan.harcmiliada.exceptions.GameNotFoundException;
import queivan.harcmiliada.mapper.GameMapper;
import queivan.harcmiliada.service.repository.GameRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@SuppressWarnings("SpellCheckingInspection")
public class GameService {
    private final GameRepository repository;
    private final LogService service;
    private final GameMapper mapper;

    public List<GameDto> getAllGames(UUID userId) {
        List<Game> games = repository.findAll();
        service.log(LogDto.builder()
                .userId(userId)
                .message("Pobrano wszystkie gry")
                .type(LogType.INFO)
                .build());
        return mapper.mapToGameDtoList(games);
    }

    public List<GameDto> getAllGamesByOwnerId(UUID id, UUID userId){
        List<Game> games = repository.findAllByOwnerId(id);
        service.log(LogDto.builder()
                        .userId(userId)
                        .message(String.format("Pobrano wszystkie gry użytkownika o id: %s", id))
                        .type(LogType.INFO)
                        .build());
        return mapper.mapToGameDtoList(games);
    }

    public GameDto getGameById(UUID id, UUID userId){
        doesGameExist(id, userId);
        Game game = repository.findById(id).orElseThrow(() -> new GameNotFoundException(id));
        service.log(LogDto.builder()
                .userId(userId)
                .message(String.format("Pobrano grę o id: %s", id))
                .type(LogType.INFO)
                .build());
        return mapper.mapToGameDto(game);
    }

    public GameDto createGame(GameDto gameDto, UUID userId){
        gameDto.setCreatedAt(LocalDateTime.now());
        Game game = repository.save(mapper.mapToGame(gameDto));
        service.log(LogDto.builder()
                .userId(userId)
                .message(String.format("Stworzone grę o nazwie: %s", gameDto.getName()))
                .type(LogType.INFO)
                .build());
        return mapper.mapToGameDto(game);
    }

    public GameDto updateGame(GameDto gameDto, UUID userId){
        doesGameExist(gameDto.getId(), userId);
        Game game = repository.save(mapper.mapToGame(gameDto));
        service.log(LogDto.builder()
                .userId(userId)
                .message(String.format("Zaktualizowano grę o id: %s", gameDto.getId()))
                .type(LogType.INFO)
                .build());
        return mapper.mapToGameDto(game);
    }

    public void deleteGame(UUID id, UUID userId){
        doesGameExist(id, userId);
        service.log(LogDto.builder()
                .userId(userId)
                .message(String.format("Usunięto grę o id: %s", id))
                .type(LogType.INFO)
                .build());
        repository.deleteById(id);
    }

    private void doesGameExist(UUID id, UUID userId) {
        if(!repository.existsById(id)){
            service.log(LogDto.builder()
                    .userId(userId)
                    .message(String.format("Gra o id: %s, nie istnieje", id))
                    .type(LogType.ERROR)
                    .build());
            throw new GameDoesntExistException(id);
        }
    }

}
