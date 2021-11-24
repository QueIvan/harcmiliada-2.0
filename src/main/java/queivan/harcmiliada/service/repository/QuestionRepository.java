package queivan.harcmiliada.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import queivan.harcmiliada.domain.Question;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID> {
    List<Question> findAllByInPublicLib(boolean b);
    List<Question> findAllByCreatorId(String id);
    //select all questions not in games questions array
    @Query(value = "select * from _questions where id not in (select questions_id from _games_questions where games_id = ?1) AND (creator_id = ?2 OR in_public_lib = true)", nativeQuery = true)
    List<Question> findAllNotInGame(UUID gameId, String userId);
}
