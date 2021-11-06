package queivan.harcmiliada.exceptions;

import java.util.UUID;

@SuppressWarnings("SpellCheckingInspection")
public class QuestionNotFoundException extends RuntimeException{
    public QuestionNotFoundException(UUID id) {
        super(String.format("Pytanie o id: %s, nie zostało znalezione w bazie danych", id));
    }
}
