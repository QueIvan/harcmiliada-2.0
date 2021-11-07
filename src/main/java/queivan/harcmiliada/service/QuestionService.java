package queivan.harcmiliada.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import queivan.harcmiliada.domain.GameQuestionDto;
import queivan.harcmiliada.domain.LogDto;
import queivan.harcmiliada.domain.enums.LogType;
import queivan.harcmiliada.domain.Question;
import queivan.harcmiliada.exceptions.QuestionDoesntExistException;
import queivan.harcmiliada.exceptions.QuestionNotFoundException;
import queivan.harcmiliada.mapper.QuestionMapper;
import queivan.harcmiliada.service.repository.QuestionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@SuppressWarnings("SpellCheckingInspection")
public class QuestionService {
    private final QuestionRepository repository;
    private final LogService service;
    private final QuestionMapper mapper;

    public List<GameQuestionDto> getAllPublicQuestions(String userId) {
        List<Question> questions = repository.findAllByIsPublic(true);
        service.log(LogDto.builder()
                .userId(userId)
                .message("Pobrano wszystkie pytania o statusie publicznym")
                .type(LogType.INFO)
                .build());
        return mapper.mapToGameQuestionDtoList(questions);
    }

    public List<GameQuestionDto> getAllQuestionsByUserId(String id, String userId) {
        List<Question> questions = repository.findAllByCreatorId(id);
        service.log(LogDto.builder()
                .userId(userId)
                .message(String.format("Pobrano wszystkie pytania użytkownika o id: %s", id))
                .type(LogType.INFO)
                .build());
        return mapper.mapToGameQuestionDtoList(questions);
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

    public GameQuestionDto getQuestionById(UUID id, String userId) {
        Question question = repository.findById(id).orElseThrow(() -> new QuestionNotFoundException(id));
        service.log(LogDto.builder()
                .userId(userId)
                .message(String.format("Pobrano pytanie o id: %s", id))
                .type(LogType.INFO)
                .build());
        return mapper.mapToGameQuestionDto(question);
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

}
