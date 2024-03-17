package repository;

import model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    @Query(value = "SELECT faculty  FROM faculty mc WHERE faculty.uuid = :uuid", nativeQuery = true)
    Optional<Faculty> findByUuid(@Param("uuid") String uuid);
}
