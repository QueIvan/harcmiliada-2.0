package queivan.harcmiliada.exceptions;

import java.util.UUID;

@SuppressWarnings("SpellCheckingInspection")
public class QuestionDoesntExistException extends RuntimeException{
    public QuestionDoesntExistException(UUID id) {
        super(String.format("Pytanie o id: %s, nie istnieje w bazie danych", id));
    }
}
