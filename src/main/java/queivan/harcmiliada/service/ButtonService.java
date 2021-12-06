package queivan.harcmiliada.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import queivan.harcmiliada.domain.Button;
import queivan.harcmiliada.domain.ButtonDto;
import queivan.harcmiliada.domain.LogDto;
import queivan.harcmiliada.domain.enums.LogType;
import queivan.harcmiliada.exceptions.GameNotFoundException;
import queivan.harcmiliada.mapper.ButtonMapper;
import queivan.harcmiliada.service.repository.ButtonRepository;
import queivan.harcmiliada.service.repository.GameRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@SuppressWarnings("SpellCheckingInspection")
public class ButtonService {
    private final ButtonRepository repository;
    private final GameRepository gameRepository;
    private final ButtonMapper mapper;
    private final LogService log;

    public UUID getButtonsGameId(String name, String userId) {
        Button button = repository.findByQrCode(name).orElseThrow(() -> new RuntimeException("Button not found"));
        log.log(LogDto.builder()
                .userId(userId)
                .message(String.format("Użytkownik %s pobrał id grupy dla przycisku %s", userId, button.getId()))
                .type(LogType.INFO)
                .build());
        return button.getCurrent().getId();
    }

    public ButtonDto createButton(ButtonDto buttonDto, String userId) {
        Button button = repository.save(mapper.mapToButton(buttonDto));
        log.log(LogDto.builder()
                .userId(userId)
                .message(String.format("Użytkownik o id %s stworzył przycisk o id %s", userId, button.getId()))
                .type(LogType.INFO)
                .build());
        return mapper.mapToButtonDto(button);
    }

    public ButtonDto setButtonsGame(UUID buttonId, UUID gameId, String userId) {
        Button button = repository.findById(buttonId).orElseThrow(() -> new RuntimeException("Button not found"));
        button.setCurrent(gameRepository.findById(gameId).orElseThrow(() -> new GameNotFoundException(gameId)));
        Button saved = repository.save(button);
        log.log(LogDto.builder()
                .userId(userId)
                .message(String.format("Użytkownik o id %s ustawił przycisk o id %s dla grupę o id %s", userId, saved.getId(), gameId))
                .type(LogType.INFO)
                .build());
        return mapper.mapToButtonDto(saved);
    }

    public void deleteButton(UUID buttonId, String userId) {
        repository.deleteById(buttonId);
        log.log(LogDto.builder()
                .userId(userId)
                .message(String.format("Użytkownik o id %s usunął przycisk o id %s", userId, buttonId))
                .type(LogType.INFO)
                .build());
    }

}
