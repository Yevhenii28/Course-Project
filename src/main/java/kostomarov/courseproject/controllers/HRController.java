package kostomarov.courseproject.controllers;

import kostomarov.courseproject.models.*;
import kostomarov.courseproject.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class HRController {
    private final EmployeeService employeeService;
    private final PersonalDataService personalDataService;
    private final DepartmentService departmentService;
    private final PositionService positionService;
    private final AttendanceService attendanceService;
    private final SalaryService salaryService;
    private final AbsenceService absenceService;

    @GetMapping("/employee/{id}/personal_data")
    public String showPersonalData(Model model, @PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        PersonalData personalData = personalDataService.getPersonalDataByEmployee_Id(id);
        model.addAttribute("employee", employee);
        model.addAttribute("personalData", personalData);
        return "personal_page";
    }

    @GetMapping("/attendances")
    public String showAttendances(Model model, @RequestParam(required = false) String filter,
                                  @RequestParam(required = false) String surname, @RequestParam(required = false) Integer year) {
        List<Attendance> attendances = null;
        int firstYear = attendanceService.getAttendancesSorted().get(0).getDate().getYear();
        List<Integer> years = new ArrayList<>();
        for (int y = firstYear; y <= LocalDate.now().getYear(); y++) {
            years.add(y);
        }
        if (filter == null) {
            attendances = attendanceService.getAttendancesSorted();
        } else if (filter != null && filter.equals("surname")) {
            attendances = attendanceService.getAttendancesByEmployeeSurname(surname);
        } else if (filter != null && filter.equals("year")) {
            attendances = attendanceService.getAttendancesByDate(year);
        }
        model.addAttribute("years", years);
        model.addAttribute("attendances", attendances);
        return "attendance";
    }

    @GetMapping("/salaries")
    public String showSalaries(Model model, @RequestParam(required = false) String filter,
                               @RequestParam(required = false) String surname, @RequestParam(required = false) Integer year) {
        List<Salary> salaries = null;
        int firstYear = salaryService.getSalariesSorted().get(0).getPay_date().getYear();
        List<Integer> years = new ArrayList<>();
        for (int y = firstYear; y <= LocalDate.now().getYear(); y++) {
            years.add(y);
        }
        if (filter == null) {
             salaries = salaryService.getSalariesSorted();
        } else if (filter != null && filter.equals("surname")) {
            salaries = salaryService.getSalariesByEmployeeSurname(surname);
        } else if (filter != null && filter.equals("year")) {
            salaries = salaryService.getSalariesByPayDate(year);
        }
        model.addAttribute("years", years);
        model.addAttribute("salaries", salaries);
        return "salaryTable";
    }

    @GetMapping("/employee/{id}/salary")
    public String showSalary(Model model, @PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        List<Salary> salaries = salaryService.getSalariesByEmployeeId(id);
        model.addAttribute("employee", employee);
        model.addAttribute("salaries", salaries);
        return "salaryTable";
    }

    @GetMapping("/absences")
    public String showAbsences(Model model, @RequestParam(required = false) String filter,
                                @RequestParam(required = false) String surname, @RequestParam(required = false) Integer year) {
        List<Absence> absences = null;
        int firstYear = absenceService.getAbsencesSorted().get(0).getStart_date().getYear();
        List<Integer> years = new ArrayList<>();
        for (int y = firstYear; y <= LocalDate.now().getYear(); y++) {
            years.add(y);
        }
        if (filter == null) {
            absences = absenceService.getAbsencesSorted();
        } else if (filter != null && filter.equals("surname")) {
            absences = absenceService.getAbsencesByEmployeeSurname(surname);
        } else if (filter != null && filter.equals("year")) {
            absences = absenceService.getAbsencesByDate(year);
        }
        model.addAttribute("years", years);
        model.addAttribute("absences", absences);
        return "absenceTable";
    }

    @GetMapping("/employee/{id}/absence")
    public String showVacation(Model model, @PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        List<Absence> absences = absenceService.getAbsencesByEmployeeId(id);
        model.addAttribute("employee", employee);
        model.addAttribute("absences", absences);
        return "absenceTable";
    }

    @GetMapping("/employee/add")
    public String addEmployee(Model model) {
        List<Department> departments = departmentService.getAllDepartments();
        List<Position> positions = positionService.getPositionsByDepartmenName(departments.get(0).getName());
        model.addAttribute("action", "add");
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departments);
        model.addAttribute("positions", positions);
        model.addAttribute("minDate", LocalDate.of(2000, 1, 1));
        model.addAttribute("maxDate", LocalDate.now());
        return "EmployeeForm";
    }

    @GetMapping("/employee/{id}/edit")
    public String editEmployee(Model model, @PathVariable Long id) {
        model.addAttribute("action", "edit");
        Employee employee = employeeService.getEmployeeById(id);
        List<Department> departments = departmentService.getAllDepartments();
        List<Position> positions = positionService.getPositionsByDepartmenName(employee.getDepartment());
        model.addAttribute("id", id);
        model.addAttribute("employee", employee);
        model.addAttribute("departments", departments);
        model.addAttribute("positions", positions);
        model.addAttribute("minDate", LocalDate.of(2000, 1, 1));
        model.addAttribute("maxDate", LocalDate.now());
        return "EmployeeForm";
    }

    @GetMapping("/positions")
    @ResponseBody
    public List<String> getPositions(@RequestParam String department) {
        List<Position> positions = positionService.getPositionsByDepartmenName(department);
        List<String> posNames = new ArrayList<>();
        for (Position p : positions) {
            posNames.add(p.getName());
        }
        return posNames;
    }

    @GetMapping("/employee/{action}/check")
    public String checkEmployee(Model model, Employee employee, @PathVariable String action) {
        boolean hasChanged = false;
        Employee previous = employeeService.getEmployeeById(employee.getId());
        if ("edit".equals(action)) {
            hasChanged = !previous.equals(employee);
        }
        if ("add".equals(action)) {
            hasChanged = true;
        }

        if (hasChanged) {
            boolean error = false;

            List<Employee> list = employeeService.getEmployees();
            for (Employee e : list) {
                if (e.equals(employee)) {
                    error = true;
                    model.addAttribute("isExistError", "Цей працівник вже є у БД.");
                    break;
                }
            }
            Department department = departmentService.getDepartmentByName(employee.getDepartment());

            if (error) {
                model.addAttribute("action", action);
                model.addAttribute("employee", employee);
                return "EmployeeForm";
            } else {
                if ("add".equals(action)) {
                    employeeService.addEmployee(employee);
                    department.setNumber_employees(department.getNumber_employees() + 1);
                    departmentService.editDepartment(department);
                } else {
                    employeeService.editEmployee(employee);
                }
            }
        }
        return "redirect:/index";
    }

    @GetMapping("/employee/{id}/personal_data/add")
    public String addPersonalData(Model model, @PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        String full_name = employee.getSurname() + " " + employee.getName() + " " + employee.getPname();
        model.addAttribute("full_name", full_name);
        model.addAttribute("personalData", new PersonalData());
        model.addAttribute("employee", employee);
        model.addAttribute("maxDate", LocalDate.of(LocalDate.now().getYear() - 18, 1, 1));
        model.addAttribute("action", "add");
        return "personalForm";
    }

    @GetMapping("/employee/{id}/personal_data/edit")
    public String editPersonalData(Model model, @PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        PersonalData personalData = personalDataService.getPersonalDataByEmployee_Id(id);
        String full_name = employee.getSurname() + " " + employee.getName() + " " + employee.getPname();
        model.addAttribute("full_name", full_name);
        model.addAttribute("personalData", new PersonalData());
        model.addAttribute("employee", employee);
        model.addAttribute("maxDate", LocalDate.of(LocalDate.now().getYear() - 18, 1, 1));
        model.addAttribute("action", "edit");
        model.addAttribute("personalData", personalData);
        return "personalForm";
    }

    @PostMapping("/employee/{id}/personal_data/{action}/check")
    public String checkPersonalData(Model model, @PathVariable Long id, @PathVariable String action, PersonalData personalData) {
        Employee employee = employeeService.getEmployeeById(id);
        boolean hasChanged = false;
        if ("edit".equals(action)) {
            Employee previous = employeeService.getEmployeeById(employee.getId());
            hasChanged = !previous.equals(employee);
        }
        if ("add".equals(action)) {
            hasChanged = true;
        }

        if (hasChanged) {
            if ("add".equals(action)) {
                personalData.setEmployee(employee);
                personalDataService.addPersonalData(personalData);
            } else {
                personalData.setEmployee(employee);
                personalDataService.editPersonalData(personalData);
            }
        }
        return "redirect:/employee/" + id + "/personal_data";
    }

    @GetMapping("/employee/{id}/salary/add")
    public String addSalary(Model model, @PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        String full_name = employee.getSurname() + " " + employee.getName() + " " + employee.getPname();
        model.addAttribute("full_name", full_name);
        model.addAttribute("employee", employee);
        return "salaryForm";
    }

    @PostMapping("/employee/{id}/salary/check")
    public String checkSalary(Model model, Salary salary, @PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        salary.setEmployee(employee);
        salaryService.addSalary(salary);
        return "redirect:/employee/" + id + "/salary";
    }

    @GetMapping("/employee/{id}/absence/add")
    public String addAbsences(Model model, @PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        String full_name = employee.getSurname() + " " + employee.getName() + " " + employee.getPname();
        model.addAttribute("full_name", full_name);
        model.addAttribute("employee", employee);
        model.addAttribute("absence", new Absence());
        return "absenceForm";
    }

    @PostMapping("/employee/{id}/absence/check")
    public String checkAbsence(Model model, Absence absence, @PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        absence.setEmployee(employee);
        absenceService.addAbsence(absence);
        return  "redirect:/employee/" + id + "/absence";
    }
}
