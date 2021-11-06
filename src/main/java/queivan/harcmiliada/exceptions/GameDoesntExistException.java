package queivan.harcmiliada.exceptions;

import java.util.UUID;

@SuppressWarnings("SpellCheckingInspection")
public class GameDoesntExistException extends RuntimeException{
    public GameDoesntExistException(UUID id) {
        super(String.format("Gra o id: %s, nie istnieje w bazie danych", id));
    }
}
