package repository;

import model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query(value = "SELECT * FROM departments WHERE uuid = :uuid",nativeQuery = true)
    Department findByFacultyUuid(String uuid);
}
