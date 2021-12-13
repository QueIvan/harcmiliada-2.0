package queivan.harcmiliada.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import queivan.harcmiliada.domain.LogDto;
import queivan.harcmiliada.facade.LogFacade;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "https://www.harcmiliada.pl/")
@RestController
@RequestMapping("/logs")
@RequiredArgsConstructor
public class LogController {
    private final LogFacade facade;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<LogDto> getAllLogs(){
        return facade.getAllLogs();
    }

}
