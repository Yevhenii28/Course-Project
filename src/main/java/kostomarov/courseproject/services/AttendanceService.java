package kostomarov.courseproject.services;

import kostomarov.courseproject.models.Attendance;
import kostomarov.courseproject.repositories.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;

    public List<Attendance> getAttendances() {
        return attendanceRepository.getAllAttendances();
    }

    public List<Attendance> getAllByEmployeeId(Long id) {
        return attendanceRepository.getAllByEmployee_Id(id);
    }
}
