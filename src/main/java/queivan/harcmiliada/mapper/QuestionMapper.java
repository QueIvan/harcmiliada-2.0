package queivan.harcmiliada.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import queivan.harcmiliada.domain.GameQuestionDto;
import queivan.harcmiliada.domain.Question;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuestionMapper {
    List<GameQuestionDto> mapToGameQuestionDtoList(List<Question> allByPublicIsTrue);

    GameQuestionDto mapToGameQuestionDto(Question question);

    Question mapToQuestion(GameQuestionDto question);
}
