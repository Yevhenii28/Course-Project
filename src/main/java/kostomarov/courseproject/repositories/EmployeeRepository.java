package kostomarov.courseproject.repositories;

import kostomarov.courseproject.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("""
            select e from Employee e
            """)
    List<Employee> getEmployees();

    Employee getEmployeeById(Long Id);

    List<Employee> getEmployeesByDepartment(String department);
}
