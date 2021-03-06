package queivan.harcmiliada.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import queivan.harcmiliada.domain.*;
import queivan.harcmiliada.domain.enums.LogType;
import queivan.harcmiliada.exceptions.GameDoesntExistException;
import queivan.harcmiliada.exceptions.GameNotFoundException;
import queivan.harcmiliada.exceptions.QuestionNotFoundException;
import queivan.harcmiliada.mapper.GameMapper;
import queivan.harcmiliada.service.repository.GameRepository;
import queivan.harcmiliada.service.repository.GroupRepository;
import queivan.harcmiliada.service.repository.QuestionRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@SuppressWarnings("SpellCheckingInspection")
public class GameService {
    private final GameRepository repository;
    private final QuestionRepository questionRepository;
    private final GroupRepository groupRepository;
    private final LogService service;
    private final GameMapper mapper;

    public GameQuestionDto getGamesCurrentQuestion(UUID id, String userId){
        doesGameExist(id, userId);
        Game game = repository.findById(id).orElseThrow(() -> new GameNotFoundException(id));
        service.log(LogDto.builder()
                .userId(userId)
                .message(String.format("Pobrano pytanie grę o id: %s", id))
                .type(LogType.INFO)
                .build());
        return mapper.mapToGameDto(game).getCurrentQuestion();
    }

    public List<GameDto> getAllGames(String userId) {
        List<Game> games = repository.findAll();
        service.log(LogDto.builder()
                .userId(userId)
                .message("Pobrano wszystkie gry")
                .type(LogType.INFO)
                .build());
        return mapper.mapToGameDtoList(games);
    }

    public List<GameDto> getAllGamesByOwnerId(String id, String userId){
        List<Group> groups = groupRepository.findByUsersContains(id);
        List<Game> allGames = new ArrayList<>(repository.findAllByOwnerId(id));
        groups.forEach(group -> allGames.addAll(repository.findAllByOwnerId(String.valueOf(group.getId()))));
        service.log(LogDto.builder()
                        .userId(userId)
                        .message(String.format("Pobrano wszystkie gry użytkownika o id: %s", id))
                        .type(LogType.INFO)
                        .build());
        return mapper.mapToGameDtoList(allGames);
    }

    public GameDto getGameById(UUID id, String userId){
        doesGameExist(id, userId);
        Game game = repository.findById(id).orElseThrow(() -> new GameNotFoundException(id));
        service.log(LogDto.builder()
                .userId(userId)
                .message(String.format("Pobrano grę o id: %s", id))
                .type(LogType.INFO)
                .build());
        return mapper.mapToGameDto(game);
    }

    public GameDto createGame(GameDto gameDto, String userId){
        gameDto.setCreatedAt(LocalDateTime.now());
        System.out.println(gameDto.getOwnerId());
        Game game = repository.save(mapper.mapToGame(gameDto));
        service.log(LogDto.builder()
                .userId(userId)
                .message(String.format("Stworzone grę o nazwie: %s", gameDto.getName()))
                .type(LogType.INFO)
                .build());
        return mapper.mapToGameDto(game);
    }

    public GameDto updateGame(GameDto gameDto, String userId){
        doesGameExist(gameDto.getId(), userId);
        Game mapped = mapper.mapToGame(gameDto);
        if(!gameDto.getQuestions().isEmpty()) {
            List<Question> questions;
            questions = gameDto.getQuestions().stream().map(questionDto -> questionRepository.findById(questionDto.getId()).orElseThrow(() -> new QuestionNotFoundException(questionDto.getId()))).collect(Collectors.toList());
            mapped.setQuestions(questions);
        }
        if(gameDto.getCurrentQuestion() != null){
            Question question;
            question = questionRepository.findById(gameDto.getCurrentQuestion().getId()).orElseThrow(() -> new QuestionNotFoundException(gameDto.getCurrentQuestion().getId()));
            mapped.setCurrentQuestion(question);
        }
        Game game = repository.save(mapped);
        service.log(LogDto.builder()
                .userId(userId)
                .message(String.format("Zaktualizowano grę o id: %s", gameDto.getId()))
                .type(LogType.INFO)
                .build());
        return mapper.mapToGameDto(game);
    }

    public void deleteGame(UUID id, String userId){
        System.out.println(id);
        doesGameExist(id, userId);
        repository.deleteReference(id);
        service.log(LogDto.builder()
                .userId(userId)
                .message(String.format("Usunięto grę o id: %s", id))
                .type(LogType.INFO)
                .build());
        repository.deleteById(id);
    }

    private void doesGameExist(UUID id, String userId) {
        if(!repository.existsById(id)){
            service.log(LogDto.builder()
                    .userId(userId)
                    .message(String.format("Gra o id: %s, nie istnieje", id))
                    .type(LogType.ERROR)
                    .build());
            throw new GameDoesntExistException(id);
        }
    }

    public GameQuestionDto setCurrent(UUID gameId, UUID questionId, String userId) {
        doesGameExist(gameId, userId);
        Game game = repository.findById(gameId).orElseThrow(() -> new GameNotFoundException(gameId));
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new QuestionNotFoundException(questionId));
        game.setCurrentQuestion(question);
        Game saved = repository.save(game);
        service.log(LogDto.builder()
                .userId(userId)
                .message(String.format("Ustawiono aktualne pytanie dla gry o id: %s", gameId))
                .type(LogType.INFO)
                .build());
        return mapper.mapToGameDto(game).getCurrentQuestion();
    }

    public void deleteMultipleGames(List<UUID> gameIds, String userId) {
        for(UUID id : gameIds){
            deleteGame(id, userId);
        }
    }
}
