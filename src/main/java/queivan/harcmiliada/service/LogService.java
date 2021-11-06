package queivan.harcmiliada.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import queivan.harcmiliada.domain.LogDto;
import queivan.harcmiliada.domain.enums.LogType;
import queivan.harcmiliada.mapper.LogMapper;
import queivan.harcmiliada.service.repository.LogRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LogService {
    private final LogRepository repository;
    private final LogMapper mapper;

    public List<LogDto> getAllLogs() {
        return mapper.mapToLogDtoList(repository.findAll());
    }

    public void log(LogDto dto) {
        dto.setCreatedAt(LocalDateTime.now());
        repository.save(mapper.mapToLogDto(dto));
        if(dto.getType().equals(LogType.INFO)) {
            log.info(dto.getMessage());
        }else if(dto.getType().equals(LogType.ERROR)) {
            log.error(dto.getMessage());
        }else log.debug(dto.getMessage());
    }

}
