package queivan.harcmiliada.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import queivan.harcmiliada.domain.LogDto;
import queivan.harcmiliada.service.LogService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LogFacade {
    private final LogService logService;

    public List<LogDto> getAllLogs() {
        return logService.getAllLogs();
    }

}
