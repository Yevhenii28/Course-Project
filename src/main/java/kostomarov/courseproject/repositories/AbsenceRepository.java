package kostomarov.courseproject.repositories;

import kostomarov.courseproject.models.Absence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long> {
    @Query("select a from Absence a")
    List<Absence> getAbsences();

    @Query("select a from Absence a order by a.start_date")
    List<Absence> getAbsencesSorted();

    List<Absence> getAbsenceByEmployee_Surname(String surname);

    @Query("select a from Absence a where year(a.start_date) = :year and year(a.end_date) = :year")
    List<Absence> getAbsencesByDateYear(int year);

    List<Absence> getAbsencesByEmployee_Id(Long id);
}
