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

    List<Attendance> getAllByEmployee_Id(Long id);
}
