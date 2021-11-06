package queivan.harcmiliada.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import queivan.harcmiliada.domain.Game;
import queivan.harcmiliada.domain.GameDto;
import queivan.harcmiliada.domain.Log;
import queivan.harcmiliada.domain.LogDto;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LogMapper {
    Log mapToLogDto(LogDto dto);

    List<LogDto> mapToLogDtoList(List<Log> all);
}
