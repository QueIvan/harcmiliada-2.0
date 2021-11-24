package queivan.harcmiliada.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import queivan.harcmiliada.domain.Game;
import queivan.harcmiliada.domain.GameQuestionDto;
import queivan.harcmiliada.domain.QuestionDto;
import queivan.harcmiliada.facade.QuestionFacade;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionFacade facade;

    @GetMapping(value = "/public/{userId}", produces = APPLICATION_JSON_VALUE)
    public List<GameQuestionDto> getAllPublicQuestions(@PathVariable("userId") String userId) {
        return facade.getAllPublicQuestions(userId);
    }

    @GetMapping(value = "/notin/{gameId}/{userId}", produces = APPLICATION_JSON_VALUE)
    public List<GameQuestionDto> getAllQuestionsNotInGame(@PathVariable("gameId") UUID gameId, @PathVariable("userId") String userId) {
        return facade.getAllQuestionsNotInGame(gameId, userId);
    }

    @GetMapping(value = "/creator/{creatorId}/{userId}", produces = APPLICATION_JSON_VALUE)
    public List<GameQuestionDto> getAllQuestionsByUserId(@PathVariable("creatorId") String creatorId, @PathVariable("userId") String userId) {
        return facade.getAllQuestionsByUserId(creatorId, userId);
    }

    @GetMapping(value = "/{questionId}/{userId}", produces = APPLICATION_JSON_VALUE)
    public QuestionDto getQuestionById(@PathVariable("questionId") UUID questionId, @PathVariable("userId") String userId) {
        return facade.getQuestionById(questionId, userId);
    }

    @GetMapping(value = "/{userId}", produces = APPLICATION_JSON_VALUE)
    public List<GameQuestionDto> getAllQuestions(@PathVariable("userId") String userId) {
        return facade.getAllQuestions(userId);
    }

    @PostMapping(value = "/{userId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public GameQuestionDto createQuestion(@RequestBody GameQuestionDto question, @PathVariable("userId") String userId) {
        return facade.createQuestion(question, userId);
    }

    @PutMapping(value = "/{userId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public GameQuestionDto updateQuestion(@RequestBody GameQuestionDto question, @PathVariable("userId") String userId) {
        return facade.updateQuestion(question, userId);
    }

    @PutMapping(value = "/{questionId}/status/{userId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public GameQuestionDto changeGameStatus(@PathVariable("questionId") UUID questionId, @PathVariable("userId") String userId) {
        return facade.changeGameStatus(questionId, userId);
    }

    @DeleteMapping(value = "/{questionId}/{userId}")
    public void deleteQuestion(@PathVariable("questionId") UUID questionId, @PathVariable("userId") String userId) {
        facade.deleteQuestion(questionId, userId);
    }

    @DeleteMapping(value="/multiple/{userId}", consumes = APPLICATION_JSON_VALUE)
    public void deleteMultipleQuestions(@RequestBody List<UUID> questionIds, @PathVariable("userId") String userId) {
        facade.deleteMultipleQuestions(questionIds, userId);
    }

}
