package kostomarov.courseproject.repositories;

import kostomarov.courseproject.models.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long> {
    @Query("select s from Salary s")
    List<Salary> getAllSalaries();

    @Query("select s from Salary s order by s.pay_date")
    List<Salary> getAllSalariesSorted();

    List<Salary> getSalariesByEmployee_Id(Long id);

    List<Salary> getSalariesByEmployee_Surname(String surname);

    @Query("select s from Salary s where year(s.pay_date) = :year")
    List<Salary> getSalariesByPay_dateYear(int year);
}
