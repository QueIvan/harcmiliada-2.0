package queivan.harcmiliada.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import queivan.harcmiliada.domain.Question;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID> {
    List<Question> findAllByIsPublic(boolean isPublic);
    List<Question> findAllByCreatorId(UUID id);
}
