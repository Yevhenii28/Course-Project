package kostomarov.courseproject.repositories;

import kostomarov.courseproject.models.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacationRepository extends JpaRepository<Vacation, Long> {
    @Query("select v from Vacation v")
    List<Vacation> getVacations();

    List<Vacation> getVacationsByEmployee_Id(Long id);
}
