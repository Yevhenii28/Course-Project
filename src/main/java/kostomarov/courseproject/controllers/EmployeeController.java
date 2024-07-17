package kostomarov.courseproject.controllers;

import kostomarov.courseproject.models.Absence;
import kostomarov.courseproject.models.Attendance;
import kostomarov.courseproject.models.Employee;
import kostomarov.courseproject.services.AbsenceService;
import kostomarov.courseproject.services.AttendanceService;
import kostomarov.courseproject.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    private final AttendanceService attendanceService;
    private final AbsenceService absenceService;

    @GetMapping("/attendance")
    public String showAttendance(Model model, @RequestParam String surname) {
        Employee employee = employeeService.getEmployeeBySurname(surname);
        List<Attendance> attendances = attendanceService.getAllByEmployeeId(employee.getId());
        model.addAttribute("employee", employee);
        model.addAttribute("attendances", attendances);
        return "attendance";
    }

    @GetMapping("/absence")
    public String showAbsence(Model model, @RequestParam String surname) {
        Employee employee = employeeService.getEmployeeBySurname(surname);
        List<Absence> absences = absenceService.getAbsencesByEmployeeId(employee.getId());
        model.addAttribute("employee", employee);
        model.addAttribute("absences", absences);
        return "absenceTable";
    }
}
