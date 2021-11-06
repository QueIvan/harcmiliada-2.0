package queivan.harcmiliada.exceptions;

import java.util.UUID;

@SuppressWarnings("SpellCheckingInspection")
public class GameNotFoundException extends RuntimeException{
    public GameNotFoundException(UUID id) {
        super(String.format("Gra o id: %s, nie została znaleziona w bazie danych", id));
    }
}
