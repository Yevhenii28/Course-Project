package kostomarov.courseproject.controllers;

import kostomarov.courseproject.models.*;
import kostomarov.courseproject.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class MainController {
    private final UserService userService;
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final PersonalDataService personalDataService;

    @GetMapping("/index")
    private String index(Model model, @RequestParam(required = false) String filter,
                         @RequestParam(required = false) String surname, @RequestParam(required = false) String department) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                if (authority.getAuthority().equals("ROLE_ADMIN")) {
                    return admin(model);
                } else if (authority.getAuthority().equals("ROLE_HR")) {
                    return hr(model, filter, surname, department);
                } else if (authority.getAuthority().equals("ROLE_EMPLOYEE")) {
                    return employee(model, authentication);
                }
            }
        }
        return "table";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    private String admin(Model model) {
        List<User> users = userService.getAllUsers();
        users.remove(0);
        model.addAttribute("users", users);
        return "table";
    }

    @PreAuthorize("hasRole('ROLE_HR')")
    private String hr(Model model, String filter, String surname, String department) {
        List<Employee> employees = new ArrayList<>();
        List<Department> departments = departmentService.getAllDepartments();
        if (filter == null) {
            employees = employeeService.getEmployeeSorted();
        } else if (filter != null && filter.equals("surname")) {
            employees.add(employeeService.findEmployeeBySurname(surname));
        } else if (filter != null && filter.equals("department")) {
            employees = employeeService.getEmployeesByDepartment(department);
        }

        model.addAttribute("employees", employees);
        model.addAttribute("departments", departments);
        model.addAttribute("filter", filter);
        return "table";
    }

    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    private String employee(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        PersonalData personalData = personalDataService.getPersonalDataByEmployee_Email(user.getEmail());
        model.addAttribute("employee", personalData.getEmployee());
        model.addAttribute("personalData", personalData);
        return "personal_page";
    }
}
