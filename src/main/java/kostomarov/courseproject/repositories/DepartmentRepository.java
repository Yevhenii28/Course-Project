package kostomarov.courseproject.repositories;

import kostomarov.courseproject.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Query("select d from Department d")
    List<Department> getDepartments();

    Department getDepartmentByName(String name);
}
