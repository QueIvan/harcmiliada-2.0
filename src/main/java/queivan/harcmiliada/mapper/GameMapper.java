package queivan.harcmiliada.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import queivan.harcmiliada.domain.Game;
import queivan.harcmiliada.domain.GameDto;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GameMapper {
    GameDto mapToGameDto(Game game);

    Game mapToGame(GameDto gameDto);

    List<GameDto> mapToGameDtoList(List<Game> games);
}
