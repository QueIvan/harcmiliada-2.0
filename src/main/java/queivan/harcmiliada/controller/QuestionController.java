package queivan.harcmiliada.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import queivan.harcmiliada.domain.GameQuestionDto;
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
    public List<GameQuestionDto> getAllPublicQuestions(@PathVariable("userId") UUID userId) {
        return facade.getAllPublicQuestions(userId);
    }

    @GetMapping(value = "/creator/{creatorId}/{userId}", produces = APPLICATION_JSON_VALUE)
    public List<GameQuestionDto> getAllQuestionsByUserId(@PathVariable("creatorId") UUID creatorId, @PathVariable("userId") UUID userId) {
        return facade.getAllQuestionsByUserId(creatorId, userId);
    }

    @GetMapping(value = "/{questionId}/{userId}", produces = APPLICATION_JSON_VALUE)
    public GameQuestionDto getQuestionById(@PathVariable("questionId") UUID questionId, @PathVariable("userId") UUID userId) {
        return facade.getQuestionById(questionId, userId);
    }

    @GetMapping(value = "/{userId}", produces = APPLICATION_JSON_VALUE)
    public List<GameQuestionDto> getAllQuestions(@PathVariable("userId") UUID userId) {
        return facade.getAllQuestions(userId);
    }

    @PostMapping(value = "/{userId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public GameQuestionDto createQuestion(@RequestBody GameQuestionDto question, @PathVariable("userId") UUID userId) {
        return facade.createQuestion(question, userId);
    }

    @PutMapping(value = "/{userId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public GameQuestionDto updateQuestion(@RequestBody GameQuestionDto question, @PathVariable("userId") UUID userId) {
        return facade.updateQuestion(question, userId);
    }

    @DeleteMapping(value = "/{questionId}/{userId}")
    public void deleteQuestion(@PathVariable("questionId") UUID questionId, @PathVariable("userId") UUID userId) {
        facade.deleteQuestion(questionId, userId);
    }



}
