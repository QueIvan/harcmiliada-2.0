package queivan.harcmiliada.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import queivan.harcmiliada.domain.Group;

import java.util.List;
import java.util.UUID;

@Repository
public interface GroupRepository extends JpaRepository<Group, UUID> {
    @Query(value = "SELECT * FROM _groups WHERE id in (select _groups_id from _groups_users where users = ?1) ", nativeQuery = true)
    List<Group> findByUsersContains(String userId);
}
