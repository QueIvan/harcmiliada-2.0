package queivan.harcmiliada.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import queivan.harcmiliada.domain.Game;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface GameRepository extends JpaRepository<Game, UUID> {
    @Modifying
    @Transactional
    @Query(value="DELETE FROM _games_questions WHERE games_id=?1", nativeQuery = true)
    void deleteReference(UUID id);
    List<Game> findAllByOwnerId(String ownerId);
}
