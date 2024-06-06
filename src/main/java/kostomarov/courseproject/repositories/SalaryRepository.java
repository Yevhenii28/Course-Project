package kostomarov.courseproject.repositories;

import kostomarov.courseproject.models.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long> {
    @Query("select s from Salary s")
    List<Salary> getAllSalaries();

    List<Salary> getSalariesByEmployee_Id(Long id);
}
