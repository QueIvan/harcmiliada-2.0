package queivan.harcmiliada.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import queivan.harcmiliada.domain.Button;
import queivan.harcmiliada.domain.ButtonDto;
import queivan.harcmiliada.domain.Question;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ButtonRepository extends JpaRepository<Button, UUID> {
    Optional<Button> findByQrCode(String name);
    List<Button> findAllByOwnerId(String ownerId);
}
