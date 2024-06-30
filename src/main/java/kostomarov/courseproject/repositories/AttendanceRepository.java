package kostomarov.courseproject.repositories;

import kostomarov.courseproject.models.Attendance;
import kostomarov.courseproject.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    @Query("""
        select a from Attendance a
    """)
    List<Attendance> getAllAttendances();

    @Query("select a from Attendance a order by a.date")
    List<Attendance> getAllAttendancesSorted();

    List<Attendance> getAllByEmployee_Id(Long id);

    List<Attendance> getAttendancesByEmployee_Surname(String surname);

    @Query("select a from Attendance a where year(a.date) = :year")
    List<Attendance> getAttendancesByDate_Year(int year);
}
