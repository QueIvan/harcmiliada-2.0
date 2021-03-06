package queivan.harcmiliada.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import queivan.harcmiliada.domain.*;
import queivan.harcmiliada.domain.enums.LogType;
import queivan.harcmiliada.exceptions.QuestionDoesntExistException;
import queivan.harcmiliada.exceptions.QuestionNotFoundException;
import queivan.harcmiliada.mapper.QuestionMapper;
import queivan.harcmiliada.service.repository.GroupRepository;
import queivan.harcmiliada.service.repository.QuestionRepository;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@SuppressWarnings("SpellCheckingInspection")
public class QuestionService {
    private final QuestionRepository repository;
    private final GroupRepository groupRepository;
    private final LogService service;
    private final QuestionMapper mapper;

    public List<GameQuestionDto> getAllPublicQuestions(String userId) {
        List<Group> groups = groupRepository.findByUsersContains(userId);
        List<Question> allQuestions = repository.findAllByInPublicLib(true);
        groups.forEach(group -> allQuestions.addAll(repository.findAllByCreatorId(String.valueOf(group.getId()))));
        service.log(LogDto.builder()
                .userId(userId)
                .message("Pobrano wszystkie pytania o statusie publicznym")
                .type(LogType.INFO)
                .build());
        return mapper.mapToGameQuestionDtoList(allQuestions);
    }

    public List<GameQuestionDto> getAllQuestionsByUserId(String id, String userId) {
        List<Group> groups = groupRepository.findByUsersContains(userId);
        List<Question> allQuestions = repository.findAllByCreatorId(id);
        groups.forEach(group -> allQuestions.addAll(repository.findAllByCreatorId(String.valueOf(group.getId()))));
        service.log(LogDto.builder()
                .userId(userId)
                .message(String.format("Pobrano wszystkie pytania użytkownika o id: %s", id))
                .type(LogType.INFO)
                .build());
        return mapper.mapToGameQuestionDtoList(allQuestions);
    }

    public List<GameQuestionDto> getAllQuestions(String userId) {
        List<Question> questions = repository.findAll();
        service.log(LogDto.builder()
                .userId(userId)
                .message("Pobrano wszystkie pytania")
                .type(LogType.INFO)
                .build());
        return mapper.mapToGameQuestionDtoList(questions);
    }

    public QuestionDto getQuestionById(UUID id, String userId) {
        Question question = repository.findById(id).orElseThrow(() -> new QuestionNotFoundException(id));
        service.log(LogDto.builder()
                .userId(userId)
                .message(String.format("Pobrano pytanie o id: %s", id))
                .type(LogType.INFO)
                .build());
        return mapper.mapToQuestionDto(question);
    }

    public GameQuestionDto createQuestion(GameQuestionDto question, String userId) {
        question.setCreatedAt(LocalDateTime.now());
        Question savedQuestion = repository.save(mapper.mapToQuestion(question));
        savedQuestion.getAnswers().forEach(answer -> {
            answer.setQuestion(Question.builder().id(savedQuestion.getId()).build());
            answer.setCreatedAt(LocalDateTime.now());
        });
        service.log(LogDto.builder()
                .userId(userId)
                .message(String.format("Utworzono pytanie o id: %s", savedQuestion.getId()))
                .type(LogType.INFO)
                .build());
        return mapper.mapToGameQuestionDto(savedQuestion);
    }

    public GameQuestionDto updateQuestion(GameQuestionDto question, String userId) {
        doesQuestionExist(question.getId(), userId);
        Question updatedQuestion = repository.save(mapper.mapToQuestion(question));
        updatedQuestion.getAnswers().forEach(answer -> answer.setQuestion(Question.builder().id(updatedQuestion.getId()).build()));
        updatedQuestion.getAnswers().forEach(answer -> {
            if(answer.getCreatedAt() == null) {
                answer.setCreatedAt(LocalDateTime.now());
            }
        });
        service.log(LogDto.builder()
                .userId(userId)
                .message(String.format("Zaktualizowano pytanie o id: %s", updatedQuestion.getId()))
                .type(LogType.INFO)
                .build());
        return mapper.mapToGameQuestionDto(updatedQuestion);
    }

    public void deleteQuestion(UUID id, String userId) {
        doesQuestionExist(id, userId);
        repository.deleteById(id);
        service.log(LogDto.builder()
                .userId(userId)
                .message(String.format("Usunięto pytanie o id: %s", id))
                .type(LogType.INFO)
                .build());
    }

    public void doesQuestionExist(UUID id, String userId) {
        if(!repository.existsById(id)){
            service.log(LogDto.builder()
                    .userId(userId)
                    .message(String.format("Pytanie o id: %s, nie istnieje", id))
                    .type(LogType.ERROR)
                    .build());
            throw new QuestionDoesntExistException(id);
        }
    }

    public List<GameQuestionDto> getAllQuestionsNotInGame(UUID gameId, String userId) {
        List<Group> groups = groupRepository.findByUsersContains(userId);
        List<Question> questions = repository.findAllNotInGame(gameId, userId);
        groups.forEach(group -> questions.addAll(repository.findAllNotInGame(gameId, group.getId().toString())));
        Set<Question> uniqueQuestions = new HashSet<>(questions);
        questions.clear();
        questions.addAll(uniqueQuestions);
        service.log(LogDto.builder()
                .userId(userId)
                .message(String.format("Pobrano wszystkie pytania które nie są przypisane do gry o id: %s", gameId))
                .type(LogType.INFO)
                .build());
        return mapper.mapToGameQuestionDtoList(questions);
    }

    public void deleteMultipleQuestions(List<UUID> questionIds, String userId) {
        for(UUID id : questionIds) {
            deleteQuestion(id, userId);
        }
        service.log(LogDto.builder()
                .userId(userId)
                .message("Usunięto wybrane pytania")
                .type(LogType.INFO)
                .build());
    }

    public GameQuestionDto changeGameStatus(UUID questionId, String userId) {
        Question question = repository.findById(questionId).orElseThrow(() -> new QuestionNotFoundException(questionId));
        question.setPublic();
        Question updatedQuestion = repository.save(question);
        service.log(LogDto.builder()
                .userId(userId)
                .message(String.format("Zmieniono status pytania o id: %s", questionId))
                .type(LogType.INFO)
                .build());
        return mapper.mapToGameQuestionDto(updatedQuestion);
    }
}
