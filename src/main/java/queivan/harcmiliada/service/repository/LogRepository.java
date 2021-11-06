package queivan.harcmiliada.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import queivan.harcmiliada.domain.Log;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LogRepository extends JpaRepository<Log, UUID> {
}
