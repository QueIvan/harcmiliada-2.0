package queivan.harcmiliada.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import queivan.harcmiliada.domain.GameQuestionDto;
import queivan.harcmiliada.service.QuestionService;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class QuestionFacade {
    private final QuestionService questionService;

    public List<GameQuestionDto> getAllPublicQuestions(UUID userId) {
        return questionService.getAllPublicQuestions(userId);
    }

    public List<GameQuestionDto> getAllQuestionsByUserId(UUID id, UUID userId) {
        return questionService.getAllQuestionsByUserId(id, userId);
    }

    public List<GameQuestionDto> getAllQuestions(UUID userId) {
        return questionService.getAllQuestions(userId);
    }

    public GameQuestionDto getQuestionById(UUID id, UUID userId) {
        return questionService.getQuestionById(id, userId);
    }

    public GameQuestionDto createQuestion(GameQuestionDto question, UUID userId) {
        return questionService.createQuestion(question, userId);
    }

    public GameQuestionDto updateQuestion(GameQuestionDto question, UUID userId) {
        return questionService.updateQuestion(question, userId);
    }

    public void deleteQuestion(UUID id, UUID userId) {
        questionService.deleteQuestion(id, userId);
    }

}
