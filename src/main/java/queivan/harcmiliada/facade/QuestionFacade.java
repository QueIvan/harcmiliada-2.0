package queivan.harcmiliada.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import queivan.harcmiliada.domain.GameQuestionDto;
import queivan.harcmiliada.domain.QuestionDto;
import queivan.harcmiliada.service.QuestionService;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class QuestionFacade {
    private final QuestionService questionService;

    public List<GameQuestionDto> getAllPublicQuestions(String userId) {
        return questionService.getAllPublicQuestions(userId);
    }

    public List<GameQuestionDto> getAllQuestionsByUserId(String id, String userId) {
        return questionService.getAllQuestionsByUserId(id, userId);
    }

    public List<GameQuestionDto> getAllQuestions(String userId) {
        return questionService.getAllQuestions(userId);
    }

    public QuestionDto getQuestionById(UUID id, String userId) {
        return questionService.getQuestionById(id, userId);
    }

    public GameQuestionDto createQuestion(GameQuestionDto question, String userId) {
        return questionService.createQuestion(question, userId);
    }

    public GameQuestionDto updateQuestion(GameQuestionDto question, String userId) {
        return questionService.updateQuestion(question, userId);
    }

    public void deleteQuestion(UUID id, String userId) {
        questionService.deleteQuestion(id, userId);
    }

    public List<GameQuestionDto> getAllQuestionsNotInGame(UUID gameId, String userId) {
        return questionService.getAllQuestionsNotInGame(gameId, userId);
    }

    public void deleteMultipleQuestions(List<UUID> questionIds, String userId) {
        questionService.deleteMultipleQuestions(questionIds, userId);
    }

    public GameQuestionDto changeGameStatus(UUID questionId, String userId) {
        return questionService.changeGameStatus(questionId, userId);
    }
}
